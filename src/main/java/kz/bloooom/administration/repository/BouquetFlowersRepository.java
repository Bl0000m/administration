package kz.bloooom.administration.repository;

import kz.bloooom.administration.domain.entity.BouquetFlowerVariety;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BouquetFlowersRepository extends JpaRepository<BouquetFlowerVariety, Long> {

}
