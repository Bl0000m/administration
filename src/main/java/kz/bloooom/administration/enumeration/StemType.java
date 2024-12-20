package kz.bloooom.administration.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StemType {
    HARD("Жесткий"),
    SOFT("Мягкий");

    private final String title;
}
