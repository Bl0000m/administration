package kz.bloooom.administration.repository;

import kz.bloooom.administration.domain.entity.BouquetStyle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BouquetStyleRepository extends JpaRepository<BouquetStyle, Long> {
}
