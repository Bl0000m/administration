package kz.bloooom.administration.repository;

import kz.bloooom.administration.domain.entity.OrderCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderCodeRepository extends JpaRepository<OrderCode, Long> {
    boolean existsByOrderCode(Long orderCode);
}
