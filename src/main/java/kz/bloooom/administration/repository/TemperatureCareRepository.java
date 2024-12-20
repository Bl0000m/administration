package kz.bloooom.administration.repository;

import kz.bloooom.administration.domain.entity.TemperatureCare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureCareRepository extends JpaRepository<TemperatureCare, Long> {
}
