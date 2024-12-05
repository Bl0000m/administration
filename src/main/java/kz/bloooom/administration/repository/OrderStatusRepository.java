package kz.bloooom.administration.repository;

import kz.bloooom.administration.domain.entity.OrderStatus;
import kz.bloooom.administration.enumeration.order_status.OrderStatusCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
    Optional<OrderStatus> getByCode(OrderStatusCode orderStatusCode);
}
