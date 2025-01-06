package kz.bloooom.administration.domain.dto.fragrance;

import io.swagger.v3.oas.annotations.media.Schema;
import kz.bloooom.administration.enumeration.Fragrance;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@Schema(description = "Краткая информация о картинке")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FragranceDto {
    @Schema(description = "Код")
    Fragrance code;

    @Schema(description = "Наименование")
    String name;
}
