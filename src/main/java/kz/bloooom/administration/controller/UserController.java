package kz.bloooom.administration.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bloooom.administration.domain.dto.keycloak.KeycloakAuthRequestDto;
import kz.bloooom.administration.domain.dto.keycloak.KeycloakAuthResponseDto;
import kz.bloooom.administration.domain.dto.user.UserCreateDto;
import kz.bloooom.administration.facade.UserFacade;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
@Tag(name = "User API", description = "Методы для работы с пользователями")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserFacade userFacade;

    @PostMapping
    @Operation(description = "Создать пользователя")
    public ResponseEntity<Void> create(@Valid @RequestBody UserCreateDto dto) {
        log.info("POST: /v1/users to create user form web admin");
        userFacade.create(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    @Operation(summary = "Получить токен доступа")
    public ResponseEntity<KeycloakAuthResponseDto> login(
            @Valid @RequestBody KeycloakAuthRequestDto keycloakAuthRequestDto
    ) {
        return ResponseEntity.ok(userFacade.login(keycloakAuthRequestDto));
    }
}
