package kz.bloooom.administration.domain.dto.additional_elements;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@Builder
@Schema(description = "Обьект для показа полной информации о доп-х элементах")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdditionalElementsNameInfoDto {
    @Schema(description = "Id")
    Long id;

    @Schema(description = "Наименованиеø")
    String name;

    @Schema(description = "Количество")
    int quantity;

    @Schema(description = "Цвет")
    String color;
}
