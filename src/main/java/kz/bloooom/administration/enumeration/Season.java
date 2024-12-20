package kz.bloooom.administration.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Season {
    WINTER("Зима"),
    SUMMER("Лето"),
    SPRING("Весна"),
    AUTUMN("Осень");

    private final String title;
}
