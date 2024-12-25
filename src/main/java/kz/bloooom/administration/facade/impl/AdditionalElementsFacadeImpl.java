package kz.bloooom.administration.facade.impl;

import kz.bloooom.administration.converter.additional_elements.AdditionalElementsCreateDtoConverter;
import kz.bloooom.administration.converter.additional_elements.AdditionalElementsInfoDtoConverter;
import kz.bloooom.administration.domain.dto.additional_elements.AdditionalElementsCreateDto;
import kz.bloooom.administration.domain.dto.additional_elements.AdditionalElementsInfoDto;
import kz.bloooom.administration.facade.AdditionalElementsFacade;
import kz.bloooom.administration.service.AdditionalElementsService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdditionalElementsFacadeImpl implements AdditionalElementsFacade {
    AdditionalElementsService additionalElementsService;
    AdditionalElementsCreateDtoConverter additionalElementsCreateDtoConverter;
    AdditionalElementsInfoDtoConverter additionalElementsInfoDtoConverter;


    @Override
    @Transactional
    public void create(AdditionalElementsCreateDto dto) {
        additionalElementsService.create(additionalElementsCreateDtoConverter.convert(dto));
    }

    @Override
    public AdditionalElementsInfoDto getById(Long id) {
        return additionalElementsInfoDtoConverter.convert(additionalElementsService.getById(id));
    }

    @Override
    public List<AdditionalElementsInfoDto> getAll() {
        return additionalElementsInfoDtoConverter.convert(additionalElementsService.getAll());
    }
}
