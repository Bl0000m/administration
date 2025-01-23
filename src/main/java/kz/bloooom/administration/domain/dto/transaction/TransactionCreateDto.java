package kz.bloooom.administration.domain.dto.transaction;

import io.swagger.v3.oas.annotations.media.Schema;
import kz.bloooom.administration.enumeration.Currency;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionCreateDto {
    @Schema(description = "Id юзера")
    Long userId;

    @Schema(description = "Сумма транзакции")
    Double amount;

    @Schema(description = "Валюта цены")
    Currency currency;

    @Schema(description = "Тип транзакции")
    Long typeId;

    @Schema(description = "Cтатус транзакции")
    Long statusId;
}
