package kz.bloooom.administration.service;

import kz.bloooom.administration.domain.entity.BouquetBranchPrice;

import java.util.List;

public interface BouquetBranchPriceService {

    BouquetBranchPrice create(BouquetBranchPrice bouquetBranchPrice);
    BouquetBranchPrice getBouquetBranchByBouquetIdAndBranchId(Long bouquetId,
                                                              Long branchId);

    List<BouquetBranchPrice> getAllBouquetBranchByBouquetId(Long bouquetId);

    List<BouquetBranchPrice> getAllBouquetBranchByBranchId(Long branchId);
}
