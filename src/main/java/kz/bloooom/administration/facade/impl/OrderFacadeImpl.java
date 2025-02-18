package kz.bloooom.administration.facade.impl;

import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.converter.order.OrderFillDtoConverter;
import kz.bloooom.administration.converter.order.OrderInfoDtoConverter;
import kz.bloooom.administration.domain.dto.order.OrderFillDto;
import kz.bloooom.administration.domain.dto.order.OrderInfoDto;
import kz.bloooom.administration.domain.dto.transaction.TransactionCreateDto;
import kz.bloooom.administration.domain.entity.Balances;
import kz.bloooom.administration.domain.entity.Order;
import kz.bloooom.administration.domain.entity.Subscription;
import kz.bloooom.administration.domain.entity.User;
import kz.bloooom.administration.enumeration.Currency;
import kz.bloooom.administration.exception.BloomAdministrationException;
import kz.bloooom.administration.facade.OrderFacade;
import kz.bloooom.administration.facade.TransactionsFacade;
import kz.bloooom.administration.service.BalancesService;
import kz.bloooom.administration.service.OrderService;
import kz.bloooom.administration.service.SubscriptionService;
import kz.bloooom.administration.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    BalancesService balancesService;
    UserService userService;
    TransactionsFacade transactionsFacade;
    SubscriptionService subscriptionService;
    OrderFillDtoConverter orderFillDtoConverter;
    OrderInfoDtoConverter orderInfoDtoConverter;

    @Override
    @Transactional
    public void fillOrder(OrderFillDto orderFillDto) {
        User user = userService.getCurrentUser();
        Balances balances = balancesService.getBalanceByUserId(user.getId());
        if (balances.getCurrentBalance() < orderFillDto.getAssemblyCost()) {
            new BloomAdministrationException(
                    HttpStatus.BAD_REQUEST,
                    ErrorCodeConstant.NOT_ENOUGH_MONEY,
                    "messages.exception.not-enough-money");
        }
        TransactionCreateDto transactionCreateDto = new TransactionCreateDto();

        transactionCreateDto.setUserId(user.getId());
        transactionCreateDto.setAmount(orderFillDto.getAssemblyCost());
        transactionCreateDto.setCurrency(Currency.KZT);
        transactionCreateDto.setTypeId(4L);
        transactionCreateDto.setStatusId(1L);
        transactionsFacade.create(transactionCreateDto);

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

    @Override
    public List<OrderInfoDto> getByBranchIdAndStatusId(
            Long branchId,
            Long statusId) {
        List<Order> orders = orderService.findAllByBranchIdAndStatusId(branchId, statusId);
        return orderInfoDtoConverter.convert(orders);
    }
}
