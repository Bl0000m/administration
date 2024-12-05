package kz.bloooom.administration.service;

import kz.bloooom.administration.domain.entity.OrderStatus;
import kz.bloooom.administration.enumeration.order_status.OrderStatusCode;

import java.util.List;

public interface OrderStatusService {
    OrderStatus getById(Long id);

    List<OrderStatus> getAllOrderStatuses();

    OrderStatus getByCode(OrderStatusCode orderStatusCode);
}
