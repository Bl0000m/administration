package kz.bloooom.administration.service.impl;

import kz.bloooom.administration.contant.ErrorCodeConstant;
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
    public AdditionalElementsPrice getByElementId(Long additionalElementId) {
        return additionalElementsPriceRepository.findByAdditionalElementsId(additionalElementId)
                .orElseThrow(() -> new BloomAdministrationException(
                        HttpStatus.NOT_FOUND,
                        ErrorCodeConstant.ELEMENT_WITH_THIS_ID_DOEST_EXISTS,
                        "messages.exception.element-not-found", additionalElementId));
    }

    @Override
    public AdditionalElementsPrice getById(Long id) {
        return additionalElementsPriceRepository.findById(id)
                .orElseThrow(() -> new BloomAdministrationException(
                        HttpStatus.NOT_FOUND,
                        ErrorCodeConstant.ELEMENT_WITH_THIS_ID_DOEST_EXISTS,
                        "messages.exception.element-not-found", id));
    }
}
