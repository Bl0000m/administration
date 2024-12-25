package kz.bloooom.administration.service.impl;

import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.domain.entity.AdditionalElements;
import kz.bloooom.administration.exception.BloomAdministrationException;
import kz.bloooom.administration.repository.AdditionalElementsRepository;
import kz.bloooom.administration.service.AdditionalElementsService;
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
public class AdditionalElementsServiceImpl implements AdditionalElementsService {


    AdditionalElementsRepository additionalElementsRepository;

    @Override
    @Transactional
    public AdditionalElements create(AdditionalElements additionalElements) {
        return additionalElementsRepository.save(additionalElements);
    }

    @Override
    public AdditionalElements getById(Long id) {
        return additionalElementsRepository.findById(id)
                .orElseThrow(() -> new BloomAdministrationException(
                        HttpStatus.NOT_FOUND,
                        ErrorCodeConstant.ELEMENT_WITH_THIS_ID_DOEST_EXISTS,
                        "messages.exception.element-not-found", id));
    }

    @Override
    public List<AdditionalElements> getAdditionalElementsByIdIn(List<Long> ids) {
        return additionalElementsRepository.findByIdIn(ids);
    }

    @Override
    public List<AdditionalElements> getAll() {
        return additionalElementsRepository.findAll();
    }
}
