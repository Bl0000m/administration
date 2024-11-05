package kz.bloooom.administration.repository;

import kz.bloooom.administration.domain.entity.Status;
import kz.bloooom.administration.enumeration.status.StatusCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    Optional<Status> getByCode(StatusCode statusCode);
}
