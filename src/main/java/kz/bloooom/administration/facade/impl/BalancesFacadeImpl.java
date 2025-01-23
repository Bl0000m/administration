package kz.bloooom.administration.facade.impl;

import kz.bloooom.administration.converter.balance.BalanceInfoDtoConverter;
import kz.bloooom.administration.domain.dto.balance.BalanceInfoDto;
import kz.bloooom.administration.facade.BalancesFacade;
import kz.bloooom.administration.service.BalancesService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BalancesFacadeImpl implements BalancesFacade {
    BalanceInfoDtoConverter balanceInfoDtoConverter;
    BalancesService balancesService;

    @Override
    public BalanceInfoDto getBalanceByUserId(Long userId) {
        return balanceInfoDtoConverter.convert(balancesService.getBalanceByUserId(userId));
    }
}
