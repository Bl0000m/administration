package kz.bloooom.administration.service;

import kz.bloooom.administration.domain.entity.Balances;

public interface BalancesService {
    Balances getBalanceByUserId(Long userId);

    Balances save(Balances balances);
}
