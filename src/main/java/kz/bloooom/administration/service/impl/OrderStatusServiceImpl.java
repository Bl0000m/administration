package kz.bloooom.administration.service.impl;

import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.domain.entity.OrderStatus;
import kz.bloooom.administration.enumeration.order_status.OrderStatusCode;
import kz.bloooom.administration.exception.BloomAdministrationException;
import kz.bloooom.administration.repository.OrderStatusRepository;
import kz.bloooom.administration.service.OrderStatusService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderStatusServiceImpl implements OrderStatusService {

    OrderStatusRepository orderStatusRepository;

    @Override
    public OrderStatus getById(Long id) {
        return orderStatusRepository.findById(id)
                .orElseThrow(() -> new BloomAdministrationException(
                        HttpStatus.NOT_FOUND,
                        ErrorCodeConstant.ORDER_STATUS_WITH_THIS_ID_DOEST_EXISTS,
                        "messages.exception.order-status-not-found", id));
    }

    @Override
    public List<OrderStatus> getAllOrderStatuses() {
        return orderStatusRepository.findAll();
    }

    @Override
    public OrderStatus getByCode(OrderStatusCode orderStatusCode) {
        return orderStatusRepository.getByCode(orderStatusCode)
                .orElseThrow(() -> new BloomAdministrationException(
                        HttpStatus.NOT_FOUND,
                        ErrorCodeConstant.ORDER_STATUS_WITH_THIS_CODE_DOEST_EXISTS,
                        "messages.exception.order-status-code-not-found", orderStatusCode));
    }
}
