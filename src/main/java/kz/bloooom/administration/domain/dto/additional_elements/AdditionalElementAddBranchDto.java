package kz.bloooom.administration.domain.dto.additional_elements;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import kz.bloooom.administration.enumeration.Currency;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdditionalElementAddBranchDto {
    @Schema(description = "Id элемента")
    Long additionalElementId;

    @Schema(description = "Цена")
    Double price;

    @NotNull(message = "Id филиала не должно быть пустым")
    @Schema(description = "Id филиала")
    Long branchDivisionId;

    @Schema(description = "Валюта")
    Currency currency;

    @Schema(description = "Дата начала действия цены")
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate validFrom;

    @Schema(description = "Дата окончания действия цены")
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate validTo;
}
