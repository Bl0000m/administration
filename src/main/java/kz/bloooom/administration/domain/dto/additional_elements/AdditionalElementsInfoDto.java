package kz.bloooom.administration.domain.dto.additional_elements;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import kz.bloooom.administration.domain.dto.branch_division.BranchDivisionInfoDto;
import kz.bloooom.administration.enumeration.Currency;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

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

    @Schema(description = "Цена")
    Double price;

    @Schema(description = "филиал")
    BranchDivisionInfoDto branchDivisionInfo;

    @Schema(description = "Валюта")
    String currency;

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
