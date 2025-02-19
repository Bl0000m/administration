package kz.bloooom.administration.service.impl;

import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.domain.dto.additional_elements.AdditionalElementAddBranchDto;
import kz.bloooom.administration.domain.entity.AdditionalElementsPrice;
import kz.bloooom.administration.exception.BloomAdministrationException;
import kz.bloooom.administration.repository.AdditionalElementsPriceRepository;
import kz.bloooom.administration.service.AdditionalElementsPriceService;
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
public class AdditionalElementsPriceServiceImpl implements AdditionalElementsPriceService {
    AdditionalElementsPriceRepository additionalElementsPriceRepository;

    @Override
    @Transactional
    public AdditionalElementsPrice create(AdditionalElementsPrice additionalElementsPrice) {
        return additionalElementsPriceRepository.save(additionalElementsPrice);
    }

    @Override
    public List<AdditionalElementsPrice> getByElementId(Long additionalElementId) {
        return additionalElementsPriceRepository.findAllByAdditionalElementsId(additionalElementId);
    }

    @Override
    public List<AdditionalElementsPrice> findAllByBranchDivisionId(Long branchId) {
        return additionalElementsPriceRepository.findAllByBranchDivisionIdAndCurrentDate(branchId, LocalDateTime.now());
    }

    @Override
    public List<AdditionalElementsPrice> getAdditionalElementsByBranchIdAndElementId(Long branchId, Long elementId) {
        return additionalElementsPriceRepository.findAllByBranchDivisionIdAndAdditionalElementsId(branchId, elementId);
    }

    @Override
    public boolean existsByDateOverlap(Long additionalElementId,
                                       Long branchDivisionId,
                                       LocalDateTime validFrom,
                                       LocalDateTime validTo) {
        return additionalElementsPriceRepository.existsByDateOverlap(additionalElementId,
                branchDivisionId,
                validFrom,
                validTo);
    }

    @Override
    public AdditionalElementsPrice getById(Long id) {
        return additionalElementsPriceRepository.findById(id)
                .orElseThrow(() -> new BloomAdministrationException(
                        HttpStatus.NOT_FOUND,
                        ErrorCodeConstant.ELEMENT_WITH_THIS_ID_DOEST_EXISTS,
                        "messages.exception.element-not-found", id));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        additionalElementsPriceRepository.deleteById(id);
    }
}
