package kz.bloooom.administration.service.impl;

import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.domain.entity.FlowerVariety;
import kz.bloooom.administration.exception.BloomAdministrationException;
import kz.bloooom.administration.repository.FlowerVarietyRepository;
import kz.bloooom.administration.service.FlowerVarietyService;
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
public class FlowerVarietyServiceImpl implements FlowerVarietyService {
    FlowerVarietyRepository flowerVarietyRepository;

    @Override
    @Transactional
    public FlowerVariety save(FlowerVariety flowerVariety) {
        return flowerVarietyRepository.save(flowerVariety);
    }

    @Override
    public FlowerVariety getById(Long id) {
        return flowerVarietyRepository.findById(id)
                .orElseThrow(() -> new BloomAdministrationException(
                        HttpStatus.NOT_FOUND,
                        ErrorCodeConstant.FLOWER_VARIETY_THIS_ID_DOEST_EXISTS,
                        "messages.exception.flower-variety-not-found", id));
    }

    @Override
    public List<FlowerVariety> getFlowerVarietiesByIdIn(List<Long> ids) {
        return flowerVarietyRepository.findByIdIn(ids);
    }

    @Override
    public List<FlowerVariety> getAll() {
        return flowerVarietyRepository.findAll();
    }
}
