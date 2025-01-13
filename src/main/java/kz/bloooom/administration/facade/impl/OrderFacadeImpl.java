package kz.bloooom.administration.facade.impl;

import kz.bloooom.administration.converter.order.OrderFillDtoConverter;
import kz.bloooom.administration.converter.order.OrderInfoDtoConverter;
import kz.bloooom.administration.domain.dto.order.OrderFillDto;
import kz.bloooom.administration.domain.dto.order.OrderInfoDto;
import kz.bloooom.administration.domain.entity.Order;
import kz.bloooom.administration.domain.entity.Subscription;
import kz.bloooom.administration.facade.OrderFacade;
import kz.bloooom.administration.service.OrderService;
import kz.bloooom.administration.service.SubscriptionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderFacadeImpl implements OrderFacade {

    OrderService orderService;
    SubscriptionService subscriptionService;
    OrderFillDtoConverter orderFillDtoConverter;
    OrderInfoDtoConverter orderInfoDtoConverter;

    @Override
    @Transactional
    public void fillOrder(OrderFillDto orderFillDto) {
        Order order = orderService.findById(orderFillDto.getId());
        orderService.save(orderFillDtoConverter.convert(orderFillDto, order));
    }

    @Override
    public List<OrderInfoDto> getOrdersBySubscriptionId(Long subscriptionId) {
        Subscription subscription = subscriptionService.findById(subscriptionId);
        List<Order> orders = orderService.findAllBySubscription(subscription);
        return orderInfoDtoConverter.convert(orders);
    }

    @Override
    public OrderInfoDto getById(Long id) {
        return orderInfoDtoConverter.convert(orderService.findById(id));
    }
}
