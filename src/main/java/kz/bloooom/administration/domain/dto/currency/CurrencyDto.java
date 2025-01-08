package kz.bloooom.administration.domain.dto.currency;

import io.swagger.v3.oas.annotations.media.Schema;
import kz.bloooom.administration.enumeration.Currency;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CurrencyDto {
    @Schema(description = "Код")
    Currency code;

    @Schema(description = "Наименование")
    String name;
}
