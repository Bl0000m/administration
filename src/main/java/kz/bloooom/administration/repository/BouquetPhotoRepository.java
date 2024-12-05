package kz.bloooom.administration.repository;

import kz.bloooom.administration.domain.entity.BouquetPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BouquetPhotoRepository extends JpaRepository<BouquetPhoto, Long> {
}
