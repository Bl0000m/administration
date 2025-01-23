package kz.bloooom.administration.enumeration.transaction_status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TransactionsStatusCode {
    ACCEPTED("Успешно"),
    REJECTED("Ошибка");

    private final String title;

}

