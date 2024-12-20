package kz.bloooom.administration.domain.dto.flower_variety;

import kz.bloooom.administration.enumeration.Fragrance;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FragranceInfoDto {
    Fragrance code;
    String name;
}
