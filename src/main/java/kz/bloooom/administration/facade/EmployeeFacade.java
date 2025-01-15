package kz.bloooom.administration.facade;

import kz.bloooom.administration.domain.dto.employee.EmployeeCreateDto;
import kz.bloooom.administration.domain.dto.employee.EmployeeMeInfoDto;
import kz.bloooom.administration.domain.dto.employee.ResetUserAuthorizationRequestDto;
import kz.bloooom.administration.domain.dto.keycloak.KeycloakAuthRequestDto;
import kz.bloooom.administration.domain.dto.keycloak.KeycloakAuthResponseDto;

public interface EmployeeFacade {
    void create(EmployeeCreateDto dto);

    KeycloakAuthResponseDto login(KeycloakAuthRequestDto keycloakAuthRequestDto);

    void userResetAuthorizationPassword(ResetUserAuthorizationRequestDto dto);

    EmployeeMeInfoDto getMe();
}
