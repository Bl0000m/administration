package kz.bloooom.administration.service.impl;

import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.domain.entity.Country;
import kz.bloooom.administration.exception.BloomAdministrationException;
import kz.bloooom.administration.repository.CountryRepository;
import kz.bloooom.administration.service.CountryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CountryServiceImpl implements CountryService {
    CountryRepository countryRepository;

    @Override
    public Country save(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public Country getById(Long id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new BloomAdministrationException(
                        HttpStatus.NOT_FOUND,
                        ErrorCodeConstant.COUNTRY_WITH_THIS_ID_DOEST_EXISTS,
                        "messages.exception.country-not-found", id));
    }

    @Override
    public List<Country> getAll() {
        return countryRepository.findAll();
    }
}
