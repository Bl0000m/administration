package kz.bloooom.administration.facade.impl;

import kz.bloooom.administration.converter.country.CountryCreateDtoConverter;
import kz.bloooom.administration.converter.country.CountryInfoDtoConverter;
import kz.bloooom.administration.domain.dto.country.CountryCreateDto;
import kz.bloooom.administration.domain.dto.country.CountryInfoDto;
import kz.bloooom.administration.facade.CountryFacade;
import kz.bloooom.administration.service.CountryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CountryFacadeImpl implements CountryFacade {
    CountryService countryService;
    CountryCreateDtoConverter countryCreateDtoConverter;
    CountryInfoDtoConverter countryInfoDtoConverter;

    @Override
    @Transactional
    public void create(CountryCreateDto dto) {
        countryService.save(countryCreateDtoConverter.convert(dto));
    }

    @Override
    public CountryInfoDto getById(Long id) {
        return countryInfoDtoConverter.convert(countryService.getById(id));
    }

    @Override
    public List<CountryInfoDto> getAll() {
        return countryInfoDtoConverter.convert(countryService.getAll());
    }
}
