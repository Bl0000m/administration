package kz.bloooom.administration.domain.dto.temperature_care;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TemperatureCareInfoDto {
    @Schema(description = "Id")
    Long id;

    @Schema(description = "Наименование")
    String name;

    @Schema(description = "Описание")
    String description;
}
