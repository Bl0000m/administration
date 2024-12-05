package kz.bloooom.administration.service.impl;

import kz.bloooom.administration.domain.entity.OrderCode;
import kz.bloooom.administration.repository.OrderCodeRepository;
import kz.bloooom.administration.service.OrderCodeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderCodeServiceImpl implements OrderCodeService {

    OrderCodeRepository orderCodeRepository;

    @Override
    public boolean existsByCode(Long orderCode) {
        return orderCodeRepository.existsByOrderCode(orderCode);
    }

    @Override
    @Transactional
    public OrderCode save(OrderCode orderCode) {
        return orderCodeRepository.save(orderCode);
    }
}
