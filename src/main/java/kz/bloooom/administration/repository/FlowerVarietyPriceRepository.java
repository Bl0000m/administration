package kz.bloooom.administration.repository;

import kz.bloooom.administration.domain.entity.FlowerVarietyPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FlowerVarietyPriceRepository extends JpaRepository<FlowerVarietyPrice, Long> {

    Optional<FlowerVarietyPrice> findByFlowerVarietyId(Long flowerVarietyId);

}
