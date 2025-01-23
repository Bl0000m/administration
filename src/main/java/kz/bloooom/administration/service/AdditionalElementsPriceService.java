package kz.bloooom.administration.service;

import kz.bloooom.administration.domain.entity.AdditionalElementsPrice;

import java.time.LocalDateTime;
import java.util.List;

public interface AdditionalElementsPriceService {
    AdditionalElementsPrice create(AdditionalElementsPrice additionalElementsPrice);

    List<AdditionalElementsPrice> getByElementId(Long additionalElementId);

    List<AdditionalElementsPrice> findAllByBranchDivisionId(Long branchId);

    boolean existsByDateOverlap(Long additionalElementId,
                                Long branchDivisionId,
                                LocalDateTime validFrom,
                                LocalDateTime validTo);

    AdditionalElementsPrice getById(Long id);
}
