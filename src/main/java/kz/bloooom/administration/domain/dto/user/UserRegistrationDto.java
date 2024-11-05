package kz.bloooom.administration.domain.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@Schema(description = "Обьект для регистрации юзера")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRegistrationDto {
    @NotBlank(message = "Поле имя пользователя не должно быть пустым")
    @Schema(description = "Имя пользователя")
    String name;

    @NotBlank(message = "Поле почта пользователя не должно быть пустым")
    @Email(message = "email должен соотвествовать формату")
    @Schema(description = "Почта пользователя")
    String email;

    @NotBlank(message = "Поле номер телефона пользователя не должно быть пустым")
    @Schema(description = "Номер телефона пользователя")
    String phoneNumber;

    @NotBlank(message = "Пароль не должно быть пустым")
    @Schema(description = "Пароль")
    String password;

    @NotBlank(message = "Подтверждение пароля не должен быть пустым")
    @Schema(description = "Подтверждение пароля")
    String confirmPassword;
}
