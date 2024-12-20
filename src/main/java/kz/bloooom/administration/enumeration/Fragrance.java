package kz.bloooom.administration.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Fragrance {
    RICH("Насыщенный"),
    PUNGENT("Резкий"),
    SLIGHT("Легкий"),
    ABSENT("Отсутствует");

    private final String title;
}
