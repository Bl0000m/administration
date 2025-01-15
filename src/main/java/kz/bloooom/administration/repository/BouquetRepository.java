package kz.bloooom.administration.repository;

import kz.bloooom.administration.domain.entity.Bouquet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BouquetRepository extends JpaRepository<Bouquet, Long> {
    List<Bouquet> findAllByEmployeeId(Long employeeId);
}