package kz.bloooom.administration.facade;

import kz.bloooom.administration.domain.dto.AbstractEnumDto;
import kz.bloooom.administration.enumeration.order_status.OrderStatusCode;

import java.util.List;

public interface OrderStatusFacade {
    List<AbstractEnumDto<OrderStatusCode>> getAllStatuses();

    AbstractEnumDto<OrderStatusCode> getStatusById(Long id);

    AbstractEnumDto<OrderStatusCode> getStatusByCode(OrderStatusCode code);
}
