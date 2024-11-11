package kz.bloooom.administration.domain.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Schema(description = "Объект смены  забытого пароля сотрудника/пользователя")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ForgotPasswordRequestDto {

    @Schema(description = "Почта сотрудника")
    @NotBlank(message = "Почта сотрудника обязательное поле")
    String email;

    @NotBlank(message = "Новый пароль - обязательное поле")
    @Schema(description = "Новый пароль")
    String newPassword;

    @NotBlank(message = "Необходимо заполнить поле с подтверждением нового пароля")
    @Schema(description = "Подтверждение нового пароля")
    String confirmNewPassword;
}
