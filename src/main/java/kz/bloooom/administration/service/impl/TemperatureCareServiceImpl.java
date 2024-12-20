package kz.bloooom.administration.service.impl;

import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.domain.entity.TemperatureCare;
import kz.bloooom.administration.exception.BloomAdministrationException;
import kz.bloooom.administration.repository.TemperatureCareRepository;
import kz.bloooom.administration.service.TemperatureCareService;
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
public class TemperatureCareServiceImpl implements TemperatureCareService {

    TemperatureCareRepository temperatureCareRepository;

    @Override
    @Transactional
    public TemperatureCare save(TemperatureCare temperatureCare) {
        return temperatureCareRepository.save(temperatureCare);
    }

    @Override
    public TemperatureCare getById(Long id) {
        return temperatureCareRepository.findById(id)
                .orElseThrow(() -> new BloomAdministrationException(
                        HttpStatus.NOT_FOUND,
                        ErrorCodeConstant.TEMPERATURA_CARE_WITH_THIS_ID_DOEST_EXISTS,
                        "messages.exception.temperature-care-not-found", id));
    }

    @Override
    public List<TemperatureCare> getAll() {
        return temperatureCareRepository.findAll();
    }
}
