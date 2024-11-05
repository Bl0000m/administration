package kz.bloooom.administration.domain.dto.company_type;

import io.swagger.v3.oas.annotations.media.Schema;
import kz.bloooom.administration.enumeration.company_type.CompanyTypeCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Schema(description = "Данные типа компании")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanyTypeDto {
    @Schema(description = "ID этой записи в БД")
    Long id;

    @Schema(description = "Отображаемое название кода")
    String name;

    @Schema(description = "Описание кода")
    String description;

    @Schema(description = "Уникальный код типа компании")
    CompanyTypeCode code;
}
