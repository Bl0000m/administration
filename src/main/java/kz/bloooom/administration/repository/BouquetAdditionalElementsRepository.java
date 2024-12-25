package kz.bloooom.administration.repository;

import kz.bloooom.administration.domain.entity.BouquetAdditionalElements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BouquetAdditionalElementsRepository extends JpaRepository<BouquetAdditionalElements, Long> {
}
