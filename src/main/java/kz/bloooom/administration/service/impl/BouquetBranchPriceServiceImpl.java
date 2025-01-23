package kz.bloooom.administration.service.impl;

import kz.bloooom.administration.domain.entity.BouquetBranchPrice;
import kz.bloooom.administration.repository.BouquetBranchPriceRepository;
import kz.bloooom.administration.service.BouquetBranchPriceService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BouquetBranchPriceServiceImpl implements BouquetBranchPriceService {
    BouquetBranchPriceRepository bouquetBranchPriceRepository;

    @Override
    @Transactional
    public BouquetBranchPrice create(BouquetBranchPrice bouquetBranchPrice) {
        return bouquetBranchPriceRepository.save(bouquetBranchPrice);
    }

    @Override
    public BouquetBranchPrice getBouquetBranchByBouquetIdAndBranchId(Long bouquetId,
                                                                     Long branchId) {
        return bouquetBranchPriceRepository.findByBouquetIdAndBranchDivisionId(bouquetId, branchId);
    }

    @Override
    public List<BouquetBranchPrice> getAllBouquetBranchByBouquetId(Long bouquetId) {
        return bouquetBranchPriceRepository.findAllByBouquetId(bouquetId);
    }

    @Override
    public List<BouquetBranchPrice> getAllBouquetBranchByBranchId(Long branchId) {
        return bouquetBranchPriceRepository.findAllByBranchDivisionId(branchId);
    }
}
