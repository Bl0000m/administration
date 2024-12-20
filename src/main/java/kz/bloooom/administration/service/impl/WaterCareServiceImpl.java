package kz.bloooom.administration.service.impl;

import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.domain.entity.WaterCare;
import kz.bloooom.administration.exception.BloomAdministrationException;
import kz.bloooom.administration.repository.WaterCareRepository;
import kz.bloooom.administration.service.WaterCareService;
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
public class WaterCareServiceImpl implements WaterCareService {

    WaterCareRepository waterCareRepository;

    @Override
    @Transactional
    public WaterCare save(WaterCare waterCare) {
        return waterCareRepository.save(waterCare);
    }

    @Override
    public WaterCare getById(Long id) {
        return waterCareRepository.findById(id)
                .orElseThrow(() -> new BloomAdministrationException(
                        HttpStatus.NOT_FOUND,
                        ErrorCodeConstant.WATER_CARE_WITH_THIS_ID_DOEST_EXISTS,
                        "messages.exception.water-care-not-found", id));
    }

    @Override
    public List<WaterCare> getAll() {
        return waterCareRepository.findAll();
    }
}
