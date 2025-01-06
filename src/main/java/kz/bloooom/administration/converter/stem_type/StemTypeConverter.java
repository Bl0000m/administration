package kz.bloooom.administration.converter.stem_type;

import kz.bloooom.administration.domain.dto.steam_type.StemTypeDto;
import kz.bloooom.administration.enumeration.StemType;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StemTypeConverter {
    public StemTypeDto convert(StemType source) {
        return StemTypeDto.builder()
                .code(source)
                .name(source.getTitle())
                .build();
    }

    public List<StemTypeDto> convert(List<StemType> sources) {
        return CollectionUtils.isEmpty(sources) ?
                Collections.emptyList() :
                sources.stream().map(this::convert).collect(Collectors.toList());
    }
}
