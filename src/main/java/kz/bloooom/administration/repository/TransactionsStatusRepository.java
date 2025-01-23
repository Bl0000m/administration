package kz.bloooom.administration.repository;

import kz.bloooom.administration.domain.entity.TransactionsStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsStatusRepository extends JpaRepository<TransactionsStatus, Long> {
}
