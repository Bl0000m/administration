package kz.bloooom.administration.facade;

import kz.bloooom.administration.domain.dto.order.OrderFillDto;
import kz.bloooom.administration.domain.dto.order.OrderInfoDto;

import java.util.List;

public interface OrderFacade {
    void fillOrder(OrderFillDto orderFillDto);
    List<OrderInfoDto> getOrdersBySubscriptionId(Long subscriptionId);

    OrderInfoDto getById(Long id);
    List<OrderInfoDto> getByBranchIdAndStatusId(Long branchId, Long statusId);
}
