package kz.bloooom.administration.facade;

import kz.bloooom.administration.domain.dto.balance.BalanceInfoDto;

public interface BalancesFacade {
    BalanceInfoDto getBalanceByUserId(Long userId);
}
