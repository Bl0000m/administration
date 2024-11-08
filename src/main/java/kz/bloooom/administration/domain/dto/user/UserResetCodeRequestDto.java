package kz.bloooom.administration.domain.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Schema(description = "Объект передачи для запроса получения кода для смены пароля")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResetCodeRequestDto {

    @NotBlank(message = "Почта не должна быть пустой!")
    @Schema(description = "Почта пользователя")
    String email;
}

