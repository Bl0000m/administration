package kz.bloooom.administration.domain.dto.branch_division;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@ToString
@Schema(description = "Обьект для отображения филиалов")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BranchDivisionCompanyInfoDto {
    @Schema(description = "Id филиала")
    Long id;

    @Schema(description = "Id компании")
    Long companyId;

    @Schema(description = "Название компании")
    String companyName;

    @Schema(description = "Адрес филиала")
    String address;

    @Schema(description = "Тип подразделения ")
    String divisionType;

    @Schema(description = "Контактный телефон")
    String phoneNumber;

    @Schema(description = "Электронная почта")
    String email;
}
