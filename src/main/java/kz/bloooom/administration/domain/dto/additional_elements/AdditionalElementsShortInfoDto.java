package kz.bloooom.administration.domain.dto.additional_elements;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Schema(description = "Краткая информация")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdditionalElementsShortInfoDto {
    @Schema(description = "Id")
    Long id;

    @Schema(description = "Количество")
    int quantity;

    @Schema(description = "Цвет")
    String color;
}
