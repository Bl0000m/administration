package kz.bloooom.administration.facade;

import kz.bloooom.administration.domain.dto.keycloak.KeycloakAuthRequestDto;
import kz.bloooom.administration.domain.dto.keycloak.KeycloakAuthResponseDto;
import kz.bloooom.administration.domain.dto.keycloak.KeycloakAuthWithRefreshTokenDto;
import kz.bloooom.administration.domain.dto.user.ForgotPasswordRequestDto;
import kz.bloooom.administration.domain.dto.user.ResetCodeValidateRequestDto;
import kz.bloooom.administration.domain.dto.user.UserRegistrationDto;
import kz.bloooom.administration.domain.dto.user.UserResetCodeRequestDto;

public interface UserFacade {
    void register(UserRegistrationDto dto);

    KeycloakAuthResponseDto login(KeycloakAuthRequestDto keycloakAuthRequestDto);

    KeycloakAuthResponseDto refresh(KeycloakAuthWithRefreshTokenDto keycloakAuthWithRefreshTokenDto);

    void forgotPassword(ForgotPasswordRequestDto forgotPasswordRequestDto);

    void sendForgotPasswordCode(UserResetCodeRequestDto userResetCodeRequestDto);

    void validateResetCode(ResetCodeValidateRequestDto resetCodeValidateRequestDto);
}
