package kz.bloooom.administration.converter.country;

import kz.bloooom.administration.domain.dto.country.CountryInfoDto;
import kz.bloooom.administration.domain.entity.Country;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CountryInfoDtoConverter {
    public CountryInfoDto convert(Country source) {
        return CountryInfoDto.builder()
                .id(source.getId())
                .name(source.getName())
                .build();
    }

    public List<CountryInfoDto> convert(List<Country> sources) {
        return CollectionUtils.isEmpty(sources) ?
                Collections.emptyList() :
                sources.stream().map(this::convert).collect(Collectors.toList());
    }
}
