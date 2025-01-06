package kz.bloooom.administration.domain.dto.season;

import io.swagger.v3.oas.annotations.media.Schema;
import kz.bloooom.administration.enumeration.Season;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SeasonDto {
    @Schema(description = "Код")
    Season code;

    @Schema(description = "Наименование")
    String name;
}
