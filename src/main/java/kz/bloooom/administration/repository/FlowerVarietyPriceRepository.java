package kz.bloooom.administration.repository;

import kz.bloooom.administration.domain.entity.FlowerVarietyPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlowerVarietyPriceRepository extends JpaRepository<FlowerVarietyPrice, Long> {

    List<FlowerVarietyPrice> findAllByFlowerVarietyId(Long flowerVarietyId);

}
