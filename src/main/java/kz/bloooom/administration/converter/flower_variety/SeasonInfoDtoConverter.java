package kz.bloooom.administration.converter.flower_variety;

import kz.bloooom.administration.domain.dto.flower_variety.SeasonInfoDto;
import kz.bloooom.administration.enumeration.Season;
import org.springframework.stereotype.Component;

@Component
public class SeasonInfoDtoConverter {
    public SeasonInfoDto convert(Season source) {
        return SeasonInfoDto.builder()
                .code(source)
                .name(source.getTitle())
                .build();
    }
}
