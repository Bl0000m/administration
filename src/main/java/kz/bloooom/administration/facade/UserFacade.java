package kz.bloooom.administration.facade;

import kz.bloooom.administration.domain.dto.keycloak.KeycloakAuthRequestDto;
import kz.bloooom.administration.domain.dto.keycloak.KeycloakAuthResponseDto;
import kz.bloooom.administration.domain.dto.user.UserCreateDto;
import kz.bloooom.administration.domain.dto.user.UserRegistrationDto;

public interface UserFacade {
    void create(UserCreateDto dto);

    void register(UserRegistrationDto dto);

    KeycloakAuthResponseDto login(KeycloakAuthRequestDto keycloakAuthRequestDto);
}
