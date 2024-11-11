package kz.bloooom.administration.domain.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Schema(description = "Объект для валидации кода восстановления пароля пользователя")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResetCodeValidateRequestDto {

    @Schema(description = "Почта пользователя")
    @NotBlank(message = "Почта не может быть пустой")
    String email;

    @Schema(description = "Код восстановления")
    @NotBlank(message = "Код восстановления не может быть пустым")
    String resetCode;
}
