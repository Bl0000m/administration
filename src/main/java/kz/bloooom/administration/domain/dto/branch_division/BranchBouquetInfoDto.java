package kz.bloooom.administration.domain.dto.branch_division;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@Schema(description = "Обьект для отображения филиалов")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BranchBouquetInfoDto {
    @Schema(description = "Id филиала")
    Long branchId;

    @Schema(description = "Наименование компании")
    String divisionType;

    @Schema(description = "Цена")
    Double price;

    @Schema(description = "Адрес филиала")
    String address;

    @Schema(description = "Контактный телефон филиала")
    String phoneNumber;

    @Schema(description = "Электронная почта филиала")
    String email;
}

