package kz.bloooom.administration.service.impl;

import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.domain.entity.FlowerVarietyPrice;
import kz.bloooom.administration.exception.BloomAdministrationException;
import kz.bloooom.administration.repository.FlowerVarietyPriceRepository;
import kz.bloooom.administration.service.FlowerVarietyPriceService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FlowerVarietyPriceServiceImpl implements FlowerVarietyPriceService {

    FlowerVarietyPriceRepository flowerVarietyPriceRepository;

    @Override
    @Transactional
    public FlowerVarietyPrice create(FlowerVarietyPrice flowerVarietyPrice) {
        return flowerVarietyPriceRepository.save(flowerVarietyPrice);
    }

    @Override
    public List<FlowerVarietyPrice> getAllByFlowerVarietyId(Long flowerVarietyId) {
        return flowerVarietyPriceRepository.findAllByFlowerVarietyId(flowerVarietyId);
    }

    @Override
    public boolean existsByDateOverlap(Long flowerVarietyId,
                                       Long branchDivisionId,
                                       LocalDateTime validFrom,
                                       LocalDateTime validTo) {
        return flowerVarietyPriceRepository.existsByDateOverlap(
                flowerVarietyId,
                branchDivisionId,
                validFrom,
                validTo);
    }

    @Override
    public FlowerVarietyPrice getById(Long id) {
        return flowerVarietyPriceRepository.findById(id)
                .orElseThrow(() -> new BloomAdministrationException(
                        HttpStatus.NOT_FOUND,
                        ErrorCodeConstant.FLOWER_VARIETY_THIS_ID_DOEST_EXISTS,
                        "messages.exception.flower-variety-not-found", id));
    }
}
