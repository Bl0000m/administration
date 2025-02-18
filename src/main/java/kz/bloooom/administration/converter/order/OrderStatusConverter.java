package kz.bloooom.administration.converter.order;


import kz.bloooom.administration.converter.AbstractEnumDtoConverter;
import kz.bloooom.administration.domain.entity.OrderStatus;
import kz.bloooom.administration.enumeration.order_status.OrderStatusCode;
import org.springframework.stereotype.Component;

@Component
public class OrderStatusConverter
        extends AbstractEnumDtoConverter<OrderStatusCode, OrderStatus> {
}
