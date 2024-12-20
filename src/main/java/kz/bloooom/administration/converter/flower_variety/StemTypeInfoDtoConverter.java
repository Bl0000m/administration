package kz.bloooom.administration.converter.flower_variety;

import kz.bloooom.administration.domain.dto.flower_variety.StemTypeInfoDto;
import kz.bloooom.administration.enumeration.StemType;
import org.springframework.stereotype.Component;

@Component
public class StemTypeInfoDtoConverter {
    public StemTypeInfoDto convert(StemType source) {
        return StemTypeInfoDto.builder()
                .code(source)
                .name(source.getTitle())
                .build();
    }
}
