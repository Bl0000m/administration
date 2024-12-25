package kz.bloooom.administration.service;

import kz.bloooom.administration.domain.entity.AdditionalElements;

import java.util.List;

public interface AdditionalElementsService {
    AdditionalElements create(AdditionalElements additionalElements);

    AdditionalElements getById(Long id);

    List<AdditionalElements> getAdditionalElementsByIdIn(List<Long> ids);


    List<AdditionalElements> getAll();
}
