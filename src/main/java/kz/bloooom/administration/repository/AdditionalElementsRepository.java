package kz.bloooom.administration.repository;

import kz.bloooom.administration.domain.entity.AdditionalElements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdditionalElementsRepository extends JpaRepository<AdditionalElements, Long> {
    List<AdditionalElements> findByIdIn(List<Long> ids);

}
