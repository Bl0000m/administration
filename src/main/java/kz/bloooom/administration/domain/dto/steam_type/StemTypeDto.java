package kz.bloooom.administration.domain.dto.steam_type;

import io.swagger.v3.oas.annotations.media.Schema;
import kz.bloooom.administration.enumeration.StemType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StemTypeDto {
    @Schema(description = "Код")
    StemType code;

    @Schema(description = "Наименование")
    String name;
}
