package kz.bloooom.administration.domain.dto.flower_variety;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Schema(description = "Краткая информация")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlowerVarietyShortInfoToAttachBouquetDto {
    @Schema(description = "Id")
    Long id;

    @Schema(description = "Количество")
    int quantity;
}
