package kz.bloooom.administration.converter.order;

import kz.bloooom.administration.domain.dto.subscription.OrderTimeDto;
import kz.bloooom.administration.domain.entity.Order;
import kz.bloooom.administration.domain.entity.Subscription;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderCreateDtoConverter {

    public Order convert(Subscription subscription, OrderTimeDto orderTimeDto) {
        Order target = new Order();
        target.setSubscription(subscription);
        target.setDeliveryDate(orderTimeDto.getOrderDate());
        target.setDeliveryStartTime(orderTimeDto.getOrderStartTime());
        target.setDeliveryEndTime(orderTimeDto.getOrderEndTime());
        target.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        target.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
        return target;
    }
}
