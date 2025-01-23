package kz.bloooom.administration.repository;

import kz.bloooom.administration.domain.entity.BouquetBranchPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BouquetBranchPriceRepository extends JpaRepository<BouquetBranchPrice, Long> {
    BouquetBranchPrice findByBouquetIdAndBranchDivisionId(Long bouquetId, Long branchId);

    List<BouquetBranchPrice> findAllByBouquetId(Long bouquetId);

    List<BouquetBranchPrice> findAllByBranchDivisionId(Long bouquetId);
}
