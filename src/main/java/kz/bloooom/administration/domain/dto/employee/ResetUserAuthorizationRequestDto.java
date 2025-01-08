package kz.bloooom.administration.domain.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Schema(description = "Новый пароль для пользователя")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResetUserAuthorizationRequestDto {

    @NotBlank(message = "Поле почта пользователя не должно быть пустым")
    @Email(message = "email должен соотвествовать формату")
    @Schema(description = "Почта пользователя")
    String email;

    @NotBlank(message = "Новый пароль - обязательное поле")
    @Schema(description = "Новый пароль")
    @Size(min = 1, max = 128)
    String newPassword;

    @NotBlank(message = "Необходимо заполнить поле с подтверждением нового пароля")
    @Schema(description = "Подтверждение нового пароля")
    @Size(min = 1, max = 128)
    String confirmNewPassword;
}
