package kz.bloooom.administration.facade.impl;

import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.converter.user.AccessTokenResponseConverter;
import kz.bloooom.administration.converter.user.UserCreateDtoConverter;
import kz.bloooom.administration.converter.user.UserRegisterDtoConverter;
import kz.bloooom.administration.domain.dto.keycloak.KeycloakAuthRequestDto;
import kz.bloooom.administration.domain.dto.keycloak.KeycloakAuthResponseDto;
import kz.bloooom.administration.domain.dto.user.UserCreateDto;
import kz.bloooom.administration.domain.dto.user.UserRegistrationDto;
import kz.bloooom.administration.domain.entity.Role;
import kz.bloooom.administration.domain.entity.User;
import kz.bloooom.administration.enumeration.role.RoleCode;
import kz.bloooom.administration.exception.BloomAdministrationException;
import kz.bloooom.administration.facade.UserFacade;
import kz.bloooom.administration.service.*;
import kz.bloooom.administration.validator.PasswordValidator;
import kz.bloooom.administration.validator.PhoneNumberValidator;
import kz.bloooom.administration.validator.UserEmailValidator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserFacadeImpl implements UserFacade {

    UserService userService;
    UserRoleService userRoleService;
    CredentialService credentialService;
    UserEmailValidator userEmailValidator;
    PhoneNumberValidator phoneNumberValidator;
    UserCreateDtoConverter userCreateDtoConverter;
    UserRegisterDtoConverter userRegisterDtoConverter;
    KeycloakService keycloakService;
    RoleService roleService;
    AccessTokenResponseConverter accessTokenResponseConverter;
    PasswordValidator passwordValidator;

    @Override
    @Transactional
    public void create(UserCreateDto dto) {
        String keycloakId = "";
        validateAndExtractPhoneNumber(dto.getEmail(), dto.getPhoneNumber());
        dto.setPhoneNumber(extractPhoneNumbers(dto.getPhoneNumber()));
        try {
            keycloakId = createKeycloakId(dto);
            User user = createUserInDatabase(dto, keycloakId);
            assignRolesToUser(dto.getRoleId(), user);

            log.info("UserServiceImpl:create: userInfoDto={}, keycloakId={}", dto, keycloakId);
//            return mailService.sendRegistrationMessage(dto.getFirstName(),
//                    dto.getLastName(),
//                    dto.getEmail(),
//                    position.getName(),
//                    position.getStructureLevel().getCompany().getName(),
//                    keycloakId);

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
    @Transactional
    public void register(UserRegistrationDto dto) {
        String keycloakId = "";
        validateAndExtractPhoneNumber(dto.getEmail(), dto.getPhoneNumber());
        dto.setPhoneNumber(extractPhoneNumbers(dto.getPhoneNumber()));
        passwordValidator.checkValid(dto);
        try {
            keycloakId = createKeycloakId(dto);
            User user = createUserInDatabase(dto, keycloakId);
            assignRolesToUser(null, user);

            log.info("UserServiceImpl:create: userInfoDto={}, keycloakId={}", dto, keycloakId);
//            return mailService.sendRegistrationMessage(dto.getFirstName(),
//                    dto.getLastName(),
//                    dto.getEmail(),
//                    position.getName(),
//                    position.getStructureLevel().getCompany().getName(),
//                    keycloakId);

        } catch (Exception e) {
            log.error(e.getMessage());
            if (StringUtils.isNotBlank(keycloakId) && Objects.nonNull(keycloakService.getRoleByKeycloakId(keycloakId))) {
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
//        boolean isUserNotDelete = userService.existsByEmailAndNotDelete(keycloakAuthRequestDto.getUsername());
//
//        if (BooleanUtils.isFalse(isUserNotDelete)) {
//            throw new BloomAdministrationException(
//                    HttpStatus.UNAUTHORIZED,
//                    ErrorCodeConstant.USER_NOT_FOUNT,
//                    "messages.exception.user-not-found", keycloakAuthRequestDto.getUsername()
//            );
//        }

        return accessTokenResponseConverter.convert(keycloakService.login(keycloakAuthRequestDto));
    }

    private void assignRolesToUser(Long roleId, User user) {
        List<RoleCode> roleCodes = new ArrayList<>(List.of(RoleCode.CLIENT));

        if (Objects.nonNull(roleId)) {
            RoleCode roleCode = roleService.getById(roleId).getCode();
            roleCodes.add(roleCode);
        }
        keycloakService.rolesUnpinAndAssignToUser(user.getKeycloakId(), roleCodes);

        List<Role> roles = roleCodes.stream().map(roleService::getByCode).collect(Collectors.toList());
        saveRolesToUser(user, roles);
    }

    @Transactional
    public void saveRolesToUser(User user, List<Role> roles) {
        userRoleService.save(user, roles);
        log.info("Save to user id: {} roles: {}", user.getId(), roles);
    }

    public String createKeycloakId(UserCreateDto dto) {
        User user = userCreateDtoConverter.convert(dto);
        String password = credentialService.generatePassword();
        return keycloakService.createUserAndGetKeycloakId(user, password);
    }

    public String createKeycloakId(UserRegistrationDto dto) {
        User user = userRegisterDtoConverter.convert(dto);
        String password = dto.getPassword();
        return keycloakService.createUserAndGetKeycloakId(user, password);
    }

    @Transactional
    public User createUserInDatabase(UserCreateDto dto, String keycloakId) {
        User user = userCreateDtoConverter.convert(dto);
        user.setKeycloakId(keycloakId);
        return userService.save(user);
    }

    @Transactional
    public User createUserInDatabase(UserRegistrationDto dto, String keycloakId) {
        User user = userRegisterDtoConverter.convert(dto);
        user.setKeycloakId(keycloakId);
        return userService.save(user);
    }

    private void validateAndExtractPhoneNumber(String email, String phoneNumber) {
        userEmailValidator.checkValid(email);
        phoneNumberValidator.checkValid(extractPhoneNumbers(phoneNumber));
    }

    private static String extractPhoneNumbers(String phoneNumber) {
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            return phoneNumber.replaceAll("\\D", "");
        }
        return "";
    }
}
