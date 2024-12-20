package kz.bloooom.administration.domain.dto.flower_variety;

import kz.bloooom.administration.enumeration.Season;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SeasonInfoDto {
    Season code;
    String name;
}
