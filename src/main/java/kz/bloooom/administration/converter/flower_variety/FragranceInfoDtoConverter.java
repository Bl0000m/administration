package kz.bloooom.administration.converter.flower_variety;

import kz.bloooom.administration.domain.dto.flower_variety.FragranceInfoDto;
import kz.bloooom.administration.enumeration.Fragrance;
import org.springframework.stereotype.Component;

@Component
public class FragranceInfoDtoConverter {
    public FragranceInfoDto convert(Fragrance source) {
        return FragranceInfoDto.builder()
                .code(source)
                .name(source.getTitle())
                .build();
    }
}
