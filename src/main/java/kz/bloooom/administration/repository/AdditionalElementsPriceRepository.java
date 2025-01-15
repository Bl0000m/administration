package kz.bloooom.administration.repository;

import kz.bloooom.administration.domain.entity.AdditionalElementsPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdditionalElementsPriceRepository extends JpaRepository<AdditionalElementsPrice, Long> {

    List<AdditionalElementsPrice> findAllByAdditionalElementsId(Long additionalElementId);
}
