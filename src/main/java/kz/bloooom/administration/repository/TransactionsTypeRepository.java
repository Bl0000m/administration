package kz.bloooom.administration.repository;

import kz.bloooom.administration.domain.entity.TransactionsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsTypeRepository extends JpaRepository<TransactionsType, Long> {
}
