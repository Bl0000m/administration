package kz.bloooom.administration.domain.dto.additional_elements;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@Schema(description = "Обьект для создания доп элемента")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdditionalElementsCreateDto {
    @NotBlank(message = "Название элемента не должно быть пустым")
    @Size(min = 1, max = 256)
    @Schema(description = "Название элемента")
    String name;

    @Schema(description = "Описание элемента")
    String description;

    @Schema(description = "Пример использования")
    String example;

    @Schema(description = "Единица измерения")
    String unitOfMeasurement;
}
