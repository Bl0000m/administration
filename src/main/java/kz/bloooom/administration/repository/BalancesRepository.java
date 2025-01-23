package kz.bloooom.administration.repository;

import kz.bloooom.administration.domain.entity.Balances;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalancesRepository extends JpaRepository<Balances, Long> {
    Balances findByUserId(Long userId);
}
