package kz.bloooom.administration.domain.dto.additional_elements;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import kz.bloooom.administration.enumeration.Currency;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

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

    @NotNull(message = "Id сотрудника не должно быть пустым")
    @Schema(description = "Id сотрудника")
    Long employeeId;

    @Schema(description = "Описание элемента")
    String description;

    @Schema(description = "Цена")
    Double price;

    @Schema(description = "Id филиала")
    Long branchDivisionId;

    @Schema(description = "Валюта")
    Currency currency;

    @Schema(description = "Дата начала действия цены")
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDateTime validFrom;

    @Schema(description = "Дата окончания действия цены")
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDateTime validTo;

    @Schema(description = "Пример использования")
    String example;

    @Schema(description = "Единица измерения")
    String unitOfMeasurement;
}
