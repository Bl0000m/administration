package kz.bloooom.administration.service;

import kz.bloooom.administration.domain.entity.AdditionalElementsPrice;

import java.util.List;

public interface AdditionalElementsPriceService {
    AdditionalElementsPrice create(AdditionalElementsPrice additionalElementsPrice);

    List<AdditionalElementsPrice> getByElementId(Long additionalElementId);

    AdditionalElementsPrice getById(Long id);
}
