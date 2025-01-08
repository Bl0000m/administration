package kz.bloooom.administration.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Currency {
    KZT("Тенге"),
    USD("Доллар"),
    EUR("Евро");

    private final String title;
}
