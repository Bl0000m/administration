package kz.bloooom.administration.repository;

import kz.bloooom.administration.domain.entity.Order;
import kz.bloooom.administration.domain.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllBySubscription(Subscription subscription);

    List<Order> findAllByBranchDivisionIdAndOrderStatusId(Long branchId, Long statusId);
}
