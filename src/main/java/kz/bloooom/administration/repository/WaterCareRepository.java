package kz.bloooom.administration.repository;

import kz.bloooom.administration.domain.entity.WaterCare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaterCareRepository extends JpaRepository<WaterCare, Long> {
}
