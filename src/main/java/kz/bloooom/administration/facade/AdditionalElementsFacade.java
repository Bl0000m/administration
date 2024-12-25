package kz.bloooom.administration.facade;

import kz.bloooom.administration.domain.dto.additional_elements.AdditionalElementsCreateDto;
import kz.bloooom.administration.domain.dto.additional_elements.AdditionalElementsInfoDto;

import java.util.List;

public interface AdditionalElementsFacade {
    void create(AdditionalElementsCreateDto dto);

    AdditionalElementsInfoDto getById(Long id);

    List<AdditionalElementsInfoDto> getAll();
}
