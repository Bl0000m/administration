package kz.bloooom.administration.facade.impl;

import kz.bloooom.administration.converter.order.OrderStatusConverter;
import kz.bloooom.administration.domain.dto.AbstractEnumDto;
import kz.bloooom.administration.enumeration.order_status.OrderStatusCode;
import kz.bloooom.administration.facade.OrderStatusFacade;
import kz.bloooom.administration.service.OrderStatusService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderStatusFacadeImpl implements OrderStatusFacade {
    OrderStatusService orderStatusService;
    OrderStatusConverter orderStatusConverter;

    @Override
    public List<AbstractEnumDto<OrderStatusCode>> getAllStatuses() {
        return orderStatusConverter.convert(orderStatusService.getAllOrderStatuses());
    }

    @Override
    public AbstractEnumDto<OrderStatusCode> getStatusById(final Long id) {
        return orderStatusConverter.convert(orderStatusService.getById(id));
    }

    @Override
    public AbstractEnumDto<OrderStatusCode> getStatusByCode(final OrderStatusCode code) {
        return orderStatusConverter.convert(orderStatusService.getByCode(code));
    }
}
