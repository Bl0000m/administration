package kz.bloooom.administration.service.impl;

import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.domain.entity.OrderAddress;
import kz.bloooom.administration.exception.BloomAdministrationException;
import kz.bloooom.administration.repository.OrderAddressRepository;
import kz.bloooom.administration.service.OrderAddressService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderAddressServiceImpl implements OrderAddressService {
    OrderAddressRepository orderAddressRepository;

    @Override
    @Transactional
    public OrderAddress save(OrderAddress address) {
        return orderAddressRepository.save(address);
    }

    @Override
    public OrderAddress getByOrderId(Long orderId) {
        return orderAddressRepository.findByOrderId(orderId).orElse(null);
    }
}
