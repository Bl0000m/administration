package kz.bloooom.administration.facade.impl;

import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.converter.employee.EmployeeCreateDtoConverter;
import kz.bloooom.administration.converter.employee.EmployeeMeInfoDtoConverter;
import kz.bloooom.administration.converter.user.AccessTokenResponseConverter;
import kz.bloooom.administration.domain.dto.employee.EmployeeCreateDto;
import kz.bloooom.administration.domain.dto.employee.EmployeeMeInfoDto;
import kz.bloooom.administration.domain.dto.employee.ResetUserAuthorizationRequestDto;
import kz.bloooom.administration.domain.dto.keycloak.KeycloakAuthRequestDto;
import kz.bloooom.administration.domain.dto.keycloak.KeycloakAuthResponseDto;
import kz.bloooom.administration.domain.entity.Employee;
import kz.bloooom.administration.enumeration.role.RoleCode;
import kz.bloooom.administration.exception.BloomAdministrationException;
import kz.bloooom.administration.facade.EmployeeFacade;
import kz.bloooom.administration.service.CredentialService;
import kz.bloooom.administration.service.EmployeeService;
import kz.bloooom.administration.service.KeycloakService;
import kz.bloooom.administration.service.MailService;
import kz.bloooom.administration.validator.EmailValidator;
import kz.bloooom.administration.validator.PhoneNumberValidator;
import kz.bloooom.administration.validator.ResetAuthorizationPasswordValidator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeFacadeImpl implements EmployeeFacade {

    EmployeeService employeeService;
    EmailValidator emailValidator;
    PhoneNumberValidator phoneNumberValidator;
    CredentialService credentialService;
    EmployeeCreateDtoConverter employeeCreateDtoConverter;
    KeycloakService keycloakService;
    MailService mailService;
    AccessTokenResponseConverter accessTokenResponseConverter;
    ResetAuthorizationPasswordValidator resetAuthorizationPasswordValidator;
    EmployeeMeInfoDtoConverter employeeMeInfoDtoConverter;


    @Override
    @Transactional
    public void create(EmployeeCreateDto dto) {
        String keycloakId = "";
        validateAndExtractPhoneNumber(dto.getEmail(), dto.getPhoneNumber());
        dto.setPhoneNumber(extractPhoneNumbers(dto.getPhoneNumber()));
        try {
            keycloakId = createKeycloakId(dto);
            Employee employee = createUserInDatabase(dto, keycloakId);
            assignRolesToUser(employee);

            log.info("UserServiceImpl:create: userInfoDto={}, keycloakId={}", dto, keycloakId);
            mailService.sendRegistrationMessageForEmployee(dto.getName(),
                    dto.getPosition(),
                    dto.getEmail(),
                    employee.getBranchDivision().getCompany().getName(),
                    keycloakId);

        } catch (Exception e) {
            log.error(e.getMessage());
            if (StringUtils.isNotBlank(keycloakId)) {
                keycloakService.deleteUserByKeycloakIdIfExists(keycloakId);
            }
            throw new BloomAdministrationException(
                    HttpStatus.BAD_REQUEST,
                    ErrorCodeConstant.CREATE_KEYCLOAK_USER_ERROR,
                    "messages.exception.create-keycloak-user-error"
            );
        }
    }

    @Override
    public KeycloakAuthResponseDto login(KeycloakAuthRequestDto keycloakAuthRequestDto) {
//        boolean isEmployeeNotDelete = employeeService.existsByEmailAndNotDelete(keycloakAuthRequestDto.getUsername());
//        if (BooleanUtils.isFalse(isEmployeeNotDelete)) {
//            throw new BloomAdministrationException(
//                    HttpStatus.UNAUTHORIZED,
//                    ErrorCodeConstant.EMPLOYEE_NOT_FOUNT,
//                    "messages.exception.employee-not-found", keycloakAuthRequestDto.getUsername()
//            );
//        }
        return accessTokenResponseConverter.convert(keycloakService.login(keycloakAuthRequestDto));
    }

    @Override
    @Transactional
    public void userResetAuthorizationPassword(ResetUserAuthorizationRequestDto dto) {
        Employee employee = employeeService.findByEmail(dto.getEmail());
        resetAuthorizationPasswordValidator.checkValid(dto);
        keycloakService.resetPassword(employee.getKeycloakId(), dto.getNewPassword());
        log.info("Employee: {} the password was updated", dto.getEmail());
    }

    @Override
    public EmployeeMeInfoDto getMe() {
        return employeeMeInfoDtoConverter.convert(employeeService.getCurrentEmployee());
    }

    @Transactional
    public Employee createUserInDatabase(EmployeeCreateDto dto, String keycloakId) {
        Employee employee = employeeCreateDtoConverter.convert(dto);
        employee.setKeycloakId(keycloakId);
        return employeeService.save(employee);
    }

    private void assignRolesToUser(Employee employee) {
        RoleCode roleCode = employee.getRole().getCode();
        keycloakService.rolesUnpinAndAssignToUser(employee.getKeycloakId(), roleCode);
    }


    public String createKeycloakId(EmployeeCreateDto dto) {
        Employee employee = employeeCreateDtoConverter.convert(dto);
        String password = credentialService.generatePassword();
        return keycloakService.createUserAndGetKeycloakId(employee, password);
    }

    private void validateAndExtractPhoneNumber(String email, String phoneNumber) {
        emailValidator.checkValid(email);
        phoneNumberValidator.checkValid(extractPhoneNumbers(phoneNumber));
    }

    private static String extractPhoneNumbers(String phoneNumber) {
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            return phoneNumber.replaceAll("\\D", "");
        }
        return "";
    }
}
