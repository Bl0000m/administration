package kz.bloooom.administration.converter.country;

import kz.bloooom.administration.domain.dto.country.CountryCreateDto;
import kz.bloooom.administration.domain.entity.Country;
import org.springframework.stereotype.Component;

@Component
public class CountryCreateDtoConverter {
    public Country convert(CountryCreateDto dto) {
        return Country.builder()
                .name(dto.getName())
                .build();
    }
}
