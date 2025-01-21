package kz.bloooom.administration.facade;

import kz.bloooom.administration.domain.dto.address.AddressInfoDto;
import kz.bloooom.administration.domain.dto.keycloak.KeycloakAuthRequestDto;
import kz.bloooom.administration.domain.dto.keycloak.KeycloakAuthResponseDto;
import kz.bloooom.administration.domain.dto.keycloak.KeycloakAuthWithRefreshTokenDto;
import kz.bloooom.administration.domain.dto.user.*;

import java.util.List;

public interface UserFacade {
    void register(UserRegistrationDto dto);

    KeycloakAuthResponseDto login(KeycloakAuthRequestDto keycloakAuthRequestDto);

    KeycloakAuthResponseDto refresh(KeycloakAuthWithRefreshTokenDto keycloakAuthWithRefreshTokenDto);

    UserMeInfoDto getMe();

    UserSubscriptionsInfoDto getMySubscriptions();

    List<AddressInfoDto> getMyAddress();

    void logout();

    void forgotPassword(ForgotPasswordRequestDto forgotPasswordRequestDto);

    void sendForgotPasswordCode(UserResetCodeRequestDto userResetCodeRequestDto);

    void validateResetCode(ResetCodeValidateRequestDto resetCodeValidateRequestDto);
}
