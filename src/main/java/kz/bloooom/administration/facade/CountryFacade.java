package kz.bloooom.administration.facade;

import kz.bloooom.administration.domain.dto.country.CountryCreateDto;
import kz.bloooom.administration.domain.dto.country.CountryInfoDto;

import java.util.List;

public interface CountryFacade {
    void create(CountryCreateDto dto);

    CountryInfoDto getById(Long id);

    List<CountryInfoDto> getAll();
}
