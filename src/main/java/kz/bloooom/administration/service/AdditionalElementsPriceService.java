package kz.bloooom.administration.service;

import kz.bloooom.administration.domain.entity.AdditionalElementsPrice;

public interface AdditionalElementsPriceService {
    AdditionalElementsPrice create(AdditionalElementsPrice additionalElementsPrice);

    AdditionalElementsPrice getByElementId(Long additionalElementId);

    AdditionalElementsPrice getById(Long id);
}
