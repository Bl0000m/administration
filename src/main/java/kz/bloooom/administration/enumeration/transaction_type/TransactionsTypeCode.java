package kz.bloooom.administration.enumeration.transaction_type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TransactionsTypeCode {
    REPLENISHMENT("Пополнение"),
    DEBIT("Списание"),
    TRANSFER("Перевод"),
    IN_THE_BLOCK("В блоке");

    private final String title;

}
