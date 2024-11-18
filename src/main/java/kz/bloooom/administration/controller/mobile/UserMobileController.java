package kz.bloooom.administration.controller.mobile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bloooom.administration.domain.dto.user.ForgotPasswordRequestDto;
import kz.bloooom.administration.domain.dto.user.ResetCodeValidateRequestDto;
import kz.bloooom.administration.domain.dto.user.UserRegistrationDto;
import kz.bloooom.administration.domain.dto.user.UserResetCodeRequestDto;
import kz.bloooom.administration.facade.UserFacade;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/client/users")
@Tag(name = "User Mobile API", description = "Методы для работы с пользователями в мобильном приложении")
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

    @PutMapping("/forgot-password")
    @Operation(summary = "Функция забыл пароль")
    public ResponseEntity<Void> forgotPassword(
            @Valid @RequestBody ForgotPasswordRequestDto forgotPasswordRequestDto) {
        log.info("PUT: /v1/client/users/forgot-password forgot password");

        userFacade.forgotPassword(forgotPasswordRequestDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reset-code")
    @Operation(summary = "Получить код для восстановления пароля")
    public ResponseEntity<Void> getForgotPasswordCode(
            @RequestBody UserResetCodeRequestDto userResetCodeRequestDto) {
        log.info("POST: /v1/client/users/reset-code to get recovery code");
        userFacade.sendForgotPasswordCode(userResetCodeRequestDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reset-code/validate")
    @Operation(summary = "Валидация кода восстановления пользователя")
    public ResponseEntity<Void> resetCodeValidate(
            @Valid @RequestBody ResetCodeValidateRequestDto resetCodeValidateRequestDto) {
        log.info("POST: /v1/client/users/reset-code/validate to validate recovery code");
        userFacade.validateResetCode(resetCodeValidateRequestDto);
        return ResponseEntity.ok().build();
    }
}
