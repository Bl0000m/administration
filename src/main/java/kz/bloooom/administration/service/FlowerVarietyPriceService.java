package kz.bloooom.administration.service;

import kz.bloooom.administration.domain.dto.flower_variety.FlowerVarietyAddBranchDto;
import kz.bloooom.administration.domain.entity.FlowerVarietyPrice;

import java.time.LocalDateTime;
import java.util.List;

public interface FlowerVarietyPriceService {
    FlowerVarietyPrice create(FlowerVarietyPrice flowerVarietyPrice);

    List<FlowerVarietyPrice> getAllByFlowerVarietyId(Long flowerVarietyId);

    boolean existsByDateOverlap(Long flowerVarietyId,
                                Long branchDivisionId,
                                LocalDateTime validFrom,
                                LocalDateTime validTo);

    List<FlowerVarietyPrice> getAllByBranchId(Long branchId);

    boolean existsByBranchDivisionIdAndDateRange(Long branchDivisionId, LocalDateTime validFrom, LocalDateTime validTo);

    List<FlowerVarietyPrice> getAllByBranchIdAndFlowerVarietyId(Long branchId, Long varietyId);

    FlowerVarietyPrice getById(Long id);

    void deleteById(Long id);
}
