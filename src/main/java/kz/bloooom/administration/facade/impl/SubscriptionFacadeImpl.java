package kz.bloooom.administration.facade.impl;

import kz.bloooom.administration.converter.order.OrderCreateDtoConverter;
import kz.bloooom.administration.converter.subscription.SubscriptionCreateDtoConverter;
import kz.bloooom.administration.converter.subscription.SubscriptionShortInfoDtoConverter;
import kz.bloooom.administration.domain.dto.subscription.SubscriptionCreateDto;
import kz.bloooom.administration.domain.dto.subscription.SubscriptionShortInfoDto;
import kz.bloooom.administration.domain.dto.subscription.OrderTimeDto;
import kz.bloooom.administration.domain.entity.Order;
import kz.bloooom.administration.domain.entity.Subscription;
import kz.bloooom.administration.facade.SubscriptionFacade;
import kz.bloooom.administration.service.OrderService;
import kz.bloooom.administration.service.SubscriptionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SubscriptionFacadeImpl implements SubscriptionFacade {
    SubscriptionService subscriptionService;
    SubscriptionCreateDtoConverter subscriptionCreateDtoConverter;
    OrderCreateDtoConverter orderCreateDtoConverter;
    SubscriptionShortInfoDtoConverter subscriptionShortInfoDtoConverter;
    OrderService orderService;


    @Override
    @Transactional
    public SubscriptionShortInfoDto create(SubscriptionCreateDto dto) {
        validateDto(dto);

        List<OrderTimeDto> sortedOrderDates = dto.getOrderDates().stream()
                .sorted(Comparator.comparing(OrderTimeDto::getOrderDate))
                .collect(Collectors.toList());

        dto.setOrderDates(sortedOrderDates);

        Subscription subscription = subscriptionCreateDtoConverter.convert(dto);
        subscriptionService.save(subscription);

        List<Order> orders = createOrders(dto.getOrderDates(), subscription);
        orderService.saveAll(orders);

        return subscriptionShortInfoDtoConverter.convert(subscription);
    }

    private void validateDto(SubscriptionCreateDto dto) {
        if (dto == null || dto.getOrderDates() == null || dto.getOrderDates().isEmpty()) {
            throw new IllegalArgumentException("Invalid subscription data: dto or order dates cannot be null or empty.");
        }
    }

    private List<Order> createOrders(List<OrderTimeDto> orderDates, Subscription subscription) {
        return orderDates.stream()
                .map(orderTime -> {
                    if (orderTime == null) {
                        throw new IllegalArgumentException("Delivery time cannot be null.");
                    }
                    return orderCreateDtoConverter.convert(subscription, orderTime);
                })
                .collect(Collectors.toList());
    }
}
