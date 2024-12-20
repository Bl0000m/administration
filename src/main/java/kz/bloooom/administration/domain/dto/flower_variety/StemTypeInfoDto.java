package kz.bloooom.administration.domain.dto.flower_variety;

import kz.bloooom.administration.enumeration.StemType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StemTypeInfoDto {
    StemType code;
    String name;
}
