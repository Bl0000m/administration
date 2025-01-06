package kz.bloooom.administration.converter.fragrance;

import kz.bloooom.administration.domain.dto.fragrance.FragranceDto;
import kz.bloooom.administration.enumeration.Fragrance;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FragranceConverter {
    public FragranceDto convert(Fragrance source) {
        return FragranceDto.builder()
                .code(source)
                .name(source.getTitle())
                .build();
    }

    public List<FragranceDto> convert(List<Fragrance> sources) {
        return CollectionUtils.isEmpty(sources) ?
                Collections.emptyList() :
                sources.stream().map(this::convert).collect(Collectors.toList());
    }

}
