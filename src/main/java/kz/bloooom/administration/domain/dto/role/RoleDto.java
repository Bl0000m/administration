package kz.bloooom.administration.domain.dto.role;

import io.swagger.v3.oas.annotations.media.Schema;
import kz.bloooom.administration.enumeration.role.RoleCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Schema(description = "Данные роли")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleDto {
    @Schema(description = "ID этой записи в БД")
    Long id;

    @Schema(description = "Отображаемое название кода")
    String name;

    @Schema(description = "Описание кода")
    String description;

    @Schema(description = "Уникальный код роли ")
    RoleCode code;
}
