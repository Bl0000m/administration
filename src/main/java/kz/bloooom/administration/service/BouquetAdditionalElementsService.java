package kz.bloooom.administration.service;

import kz.bloooom.administration.domain.entity.BouquetAdditionalElements;

import java.util.List;

public interface BouquetAdditionalElementsService {
    void saveAll(List<BouquetAdditionalElements> bouquetAdditionalElementsList);

    BouquetAdditionalElements getByAdditionalElementIdAndBouquetId(Long additionalElementId, Long bouquetId);

}
