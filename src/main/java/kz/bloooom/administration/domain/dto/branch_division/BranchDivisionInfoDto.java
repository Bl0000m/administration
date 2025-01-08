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
public class BranchDivisionInfoDto {
    @Schema(description = "Адрес филиала")
    String address;

    @Schema(description = "Контактный телефон")
    String phoneNumber;

    @Schema(description = "Электронная почта")
    String email;
}
