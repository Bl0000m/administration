package kz.bloooom.administration.repository;

import kz.bloooom.administration.domain.entity.FlowerVariety;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlowerVarietyRepository extends JpaRepository<FlowerVariety, Long> {
    List<FlowerVariety> findByIdIn(List<Long> ids);

}
