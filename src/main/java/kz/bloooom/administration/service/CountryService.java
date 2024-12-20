package kz.bloooom.administration.service;

import kz.bloooom.administration.domain.entity.Country;

import java.util.List;

public interface CountryService {
    Country save(Country country);

    Country getById(Long id);

    List<Country> getAll();
}
