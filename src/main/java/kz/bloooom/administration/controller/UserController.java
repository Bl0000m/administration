package kz.bloooom.administration.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bloooom.administration.domain.dto.address.AddressInfoDto;
import kz.bloooom.administration.domain.dto.keycloak.KeycloakAuthRequestDto;
import kz.bloooom.administration.domain.dto.keycloak.KeycloakAuthResponseDto;
import kz.bloooom.administration.domain.dto.keycloak.KeycloakAuthWithRefreshTokenDto;
import kz.bloooom.administration.domain.dto.user.UserMeInfoDto;
import kz.bloooom.administration.domain.dto.user.UserSubscriptionsInfoDto;
import kz.bloooom.administration.facade.UserFacade;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
@Tag(name = "User API", description = "Методы для работы с пользователями в админ системе")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserFacade userFacade;

    @PostMapping("/login")
    @Operation(summary = "Получить токен доступа")
    public ResponseEntity<KeycloakAuthResponseDto> login(
            @Valid @RequestBody KeycloakAuthRequestDto keycloakAuthRequestDto
    ) {
        return ResponseEntity.ok(userFacade.login(keycloakAuthRequestDto));
    }

    @PostMapping("/refresh")
    @Operation(summary = "Обновить токен доступа")
    public ResponseEntity<KeycloakAuthResponseDto> refresh(
            @Valid @RequestBody KeycloakAuthWithRefreshTokenDto keycloakAuthWithRefreshTokenDto) {
        log.info("POST /v1/users/refresh");
        return ResponseEntity.ok(userFacade.refresh(keycloakAuthWithRefreshTokenDto));
    }

    @GetMapping("/me")
    @Operation(summary = "Получить информацию о текущем пользователе")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<UserMeInfoDto> getMe() {
        return ResponseEntity.ok(userFacade.getMe());
    }

    @GetMapping("/my-subscriptions")
    @Operation(summary = "Получить информацию подписках пользователя")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<UserSubscriptionsInfoDto> getMySubscriptions() {
        return ResponseEntity.ok(userFacade.getMySubscriptions());
    }

    @GetMapping("/my-address")
    @Operation(summary = "Получить информацию адресах пользователя")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<AddressInfoDto>> getMyAddress() {
        return ResponseEntity.ok(userFacade.getMyAddress());
    }

    @PostMapping("/logout")
    @Operation(summary = "Метод для выхода из системы")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Void> logout() {
        userFacade.logout();
        return ResponseEntity.ok().build();
    }
}
