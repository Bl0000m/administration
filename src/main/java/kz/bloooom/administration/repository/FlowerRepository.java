package kz.bloooom.administration.repository;

import kz.bloooom.administration.domain.entity.Flower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlowerRepository extends JpaRepository<Flower, Long> {
    List<Flower> findByIdIn(List<Long> ids);
}
