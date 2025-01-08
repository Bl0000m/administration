package kz.bloooom.administration.facade.impl;

import kz.bloooom.administration.config.KeycloakComponent;
import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.converter.user.AccessTokenResponseConverter;
import kz.bloooom.administration.converter.user.UserMeInfoDtoConverter;
import kz.bloooom.administration.converter.user.UserRegisterDtoConverter;
import kz.bloooom.administration.converter.user.UserSubscriptionsInfoDtoConverter;
import kz.bloooom.administration.domain.dto.keycloak.KeycloakAuthRequestDto;
import kz.bloooom.administration.domain.dto.keycloak.KeycloakAuthResponseDto;
import kz.bloooom.administration.domain.dto.keycloak.KeycloakAuthWithRefreshTokenDto;
import kz.bloooom.administration.domain.dto.user.*;
import kz.bloooom.administration.domain.entity.User;
import kz.bloooom.administration.enumeration.role.RoleCode;
import kz.bloooom.administration.exception.BloomAdministrationException;
import kz.bloooom.administration.facade.UserFacade;
import kz.bloooom.administration.service.KeycloakService;
import kz.bloooom.administration.service.MailService;
import kz.bloooom.administration.service.UserResetCodeService;
import kz.bloooom.administration.service.UserService;
import kz.bloooom.administration.util.JwtUtils;
import kz.bloooom.administration.validator.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserFacadeImpl implements UserFacade {

    UserService userService;
    EmailValidator emailValidator;
    PhoneNumberValidator phoneNumberValidator;
    UserRegisterDtoConverter userRegisterDtoConverter;
    KeycloakService keycloakService;
    AccessTokenResponseConverter accessTokenResponseConverter;
    PasswordValidator passwordValidator;
    MailService mailService;
    UserResetCodeService userResetCodeService;
    ResetCodeValidator resetCodeValidator;
    ForgotPasswordValidator forgotPasswordValidator;
    UserMeInfoDtoConverter userMeInfoDtoConverter;
    KeycloakComponent keycloakComponent;
    UserSubscriptionsInfoDtoConverter userSubscriptionsInfoDtoConverter;

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
            assignRolesToUser(user);
            String code = userResetCodeService.getUserResetCode(dto.getEmail());

            log.info("UserServiceImpl:create: userInfoDto={}, keycloakId={}", dto, keycloakId);
            // отправка сообщении на указанную почту

            mailService.sendRegistrationMessage(dto.getName(),
                    dto.getEmail(),
                    code,
                    keycloakId);

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
        boolean isUserNotDelete = userService.existsByEmailAndNotDelete(keycloakAuthRequestDto.getUsername());

        if (BooleanUtils.isFalse(isUserNotDelete) && !keycloakAuthRequestDto.getUsername().equals("bloom-admin@bloom.kz")) {
            throw new BloomAdministrationException(
                    HttpStatus.UNAUTHORIZED,
                    ErrorCodeConstant.USER_NOT_FOUNT,
                    "messages.exception.user-not-found", keycloakAuthRequestDto.getUsername()
            );
        }

        boolean isVerify = userService.isVerifyEmail(keycloakAuthRequestDto.getUsername());

        if (BooleanUtils.isFalse(isVerify) && !keycloakAuthRequestDto.getUsername().equals("bloom-admin@bloom.kz")) {
            throw new BloomAdministrationException(
                    HttpStatus.UNAUTHORIZED,
                    ErrorCodeConstant.USER_NOT_VERIFY_EMAIL,
                    "messages.exception.user-not-verify-email", keycloakAuthRequestDto.getUsername()
            );
        }

        return accessTokenResponseConverter.convert(keycloakService.login(keycloakAuthRequestDto));
    }

    @Override
    public KeycloakAuthResponseDto refresh(KeycloakAuthWithRefreshTokenDto keycloakAuthWithRefreshTokenDto) {
        return accessTokenResponseConverter.convert(
                keycloakService.refresh(keycloakAuthWithRefreshTokenDto.getRefreshToken()));
    }

    @Override
    public UserMeInfoDto getMe() {
        return userMeInfoDtoConverter.convert(userService.getCurrentUser());
    }

    @Override
    public UserSubscriptionsInfoDto getMySubscriptions() {
        User user = userService.getCurrentUser();
        return userSubscriptionsInfoDtoConverter.convert(user);
    }

    @Override
    public void logout() {
        keycloakService.logout();
    }

    @Override
    @Transactional
    public void forgotPassword(ForgotPasswordRequestDto forgotPasswordRequestDto) {
        forgotPasswordValidator.validate(forgotPasswordRequestDto);
        String keycloakId = userService.getByEmail(forgotPasswordRequestDto.getEmail()).getKeycloakId();
        keycloakService.forgotPassword(keycloakId, forgotPasswordRequestDto.getNewPassword());
    }

    @Override
    @Transactional
    public void sendForgotPasswordCode(UserResetCodeRequestDto userResetCodeRequestDto) {
        String resetCode = userResetCodeService.getUserResetCode(userResetCodeRequestDto.getEmail());
        mailService.sendForgotPasswordCode(userResetCodeRequestDto, resetCode);
    }

    @Override
    @Transactional
    public void validateResetCode(ResetCodeValidateRequestDto resetCodeValidateRequestDto) {
        resetCodeValidator.validate(resetCodeValidateRequestDto);
        userResetCodeService.deleteUserResetCode(resetCodeValidateRequestDto);
        User user = userService.getByEmail(resetCodeValidateRequestDto.getEmail());
        user.setVerify(true);
        userService.save(user);
    }

    private void assignRolesToUser(User user) {
        RoleCode roleCode = user.getRole().getCode();
        keycloakService.rolesUnpinAndAssignToUser(user.getKeycloakId(), roleCode);
    }


    public String createKeycloakId(UserRegistrationDto dto) {
        User user = userRegisterDtoConverter.convert(dto);
        String password = dto.getPassword();
        return keycloakService.createUserAndGetKeycloakId(user, password);
    }

    @Transactional
    public User createUserInDatabase(UserRegistrationDto dto, String keycloakId) {
        User user = userRegisterDtoConverter.convert(dto);
        user.setKeycloakId(keycloakId);
        return userService.save(user);
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
