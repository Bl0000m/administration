package kz.bloooom.administration.service;

import kz.bloooom.administration.domain.entity.OrderCode;

public interface OrderCodeService {
    boolean existsByCode(Long orderCode);

    OrderCode save(OrderCode orderCode);
}
