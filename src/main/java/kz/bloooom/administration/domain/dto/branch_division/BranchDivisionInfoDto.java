package kz.bloooom.administration.domain.dto.branch_division;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@Schema(description = "Обьект для отображения филиалов")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BranchDivisionInfoDto {
    @Schema(description = "Id филиала")
    Long id;

    @Schema(description = "Цена")
    Double price;

    @Schema(description = "Адрес филиала")
    String address;

    @Schema(description = "Тип подразделения ")
    String divisionType;

    @Schema(description = "Контактный телефон")
    String phoneNumber;

    @Schema(description = "Электронная почта")
    String email;

    @Schema(description = "Валюта")
    String currency;

    @Schema(description = "Дата начала действия цены")
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDateTime validFrom;

    @Schema(description = "Дата окончания действия цены")
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDateTime validTo;
}
