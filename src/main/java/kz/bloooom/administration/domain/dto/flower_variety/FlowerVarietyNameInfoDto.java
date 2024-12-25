package kz.bloooom.administration.domain.dto.flower_variety;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlowerVarietyNameInfoDto {
    @Schema(description = "Id")
    Long id;

    @Schema(description = "Количество")
    String name;

    @Schema(description = "Фото")
    String image;

    @Schema(description = "Количество")
    int quantity;
}
