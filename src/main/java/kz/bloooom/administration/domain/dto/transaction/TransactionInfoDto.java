package kz.bloooom.administration.domain.dto.transaction;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionInfoDto {
    @Schema(description = "Юзер")
    String userName;

    @Schema(description = "Сумма транзакции")
    Double amount;

    @Schema(description = "Валюта цены")
    String currency;

    @Schema(description = "Тип транзакции")
    String type;

    @Schema(description = "Cтатус транзакции")
    String status;
}
