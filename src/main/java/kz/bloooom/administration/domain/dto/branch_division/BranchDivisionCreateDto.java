package kz.bloooom.administration.domain.dto.branch_division;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@Schema(description = "Обьект для создания филиала")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BranchDivisionCreateDto {
    @NotNull(message = "Id компании не должно быть пустым")
    @Schema(description = "Id компании")
    Long companyId;

    @Schema(description = "Адрес филиала")
    String address;

    @Schema(description = "Контактный телефон")
    String phoneNumber;

    @Schema(description = "Электронная почта")
    String email;
}
