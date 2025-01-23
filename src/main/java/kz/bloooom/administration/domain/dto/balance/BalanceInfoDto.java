package kz.bloooom.administration.domain.dto.balance;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BalanceInfoDto {
    @Schema(description = "Id Юзер")
    Long userId;

    @Schema(description = "Юзер")
    String userName;

    @Schema(description = "Сумма транзакции")
    Double currentBalance;

    @Schema(description = "Валюта цены")
    String currency;
}
