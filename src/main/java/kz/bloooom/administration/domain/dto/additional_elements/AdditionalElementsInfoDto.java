package kz.bloooom.administration.domain.dto.additional_elements;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@Schema(description = "Обьект для показа полной информации о доп-х элементах")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdditionalElementsInfoDto {

    @Schema(description = "Id элемента")
    Long id;

    @Schema(description = "Название элемента")
    String name;

    @Schema(description = "Описание элемента")
    String description;

    @Schema(description = "Пример использования")
    String example;

    @Schema(description = "Единица измерения")
    String unitOfMeasurement;
}
