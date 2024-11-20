package kz.bloooom.administration.domain.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Schema(description = "Объект передачи данных для получения информации о пользователе и его кредов")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserMeInfoDto {

    @Schema(description = "Id пользователя")
    Long id;

    @Schema(description = "ФИО человека")
    String name;

    @Schema(description = "Телефон пользователя")
    String phoneNumber;

    @Schema(description = "Почта пользователя")
    String email;
}
