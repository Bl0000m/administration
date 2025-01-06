package kz.bloooom.administration.converter.season;

import kz.bloooom.administration.domain.dto.season.SeasonDto;
import kz.bloooom.administration.enumeration.Season;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SeasonConverter {
    public SeasonDto convert(Season source) {
        return SeasonDto.builder()
                .code(source)
                .name(source.getTitle())
                .build();
    }

    public List<SeasonDto> convert(List<Season> sources) {
        return CollectionUtils.isEmpty(sources) ?
                Collections.emptyList() :
                sources.stream().map(this::convert).collect(Collectors.toList());
    }
}
