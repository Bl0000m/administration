package kz.bloooom.administration.repository;

import kz.bloooom.administration.domain.entity.StemCare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StemCareRepository extends JpaRepository<StemCare, Long> {
}
