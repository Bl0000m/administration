package kz.bloooom.administration.controller.mobile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bloooom.administration.domain.dto.keycloak.KeycloakAuthRequestDto;
import kz.bloooom.administration.domain.dto.keycloak.KeycloakAuthResponseDto;
import kz.bloooom.administration.domain.dto.user.UserCreateDto;
import kz.bloooom.administration.domain.dto.user.UserRegistrationDto;
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
@RequestMapping("/v1/client/users")
@Tag(name = "User Mobile API", description = "Методы для работы с пользователями в мобиольном приложении")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserMobileController {

    UserFacade userFacade;

    @PostMapping
    @Operation(summary = "Регистрация пользователя")
    public ResponseEntity<Void> create(@Valid @RequestBody UserRegistrationDto dto) {
        log.info("POST: /v1/client/users register user form mobile");
        userFacade.register(dto);
        return ResponseEntity.ok().build();
    }
}
