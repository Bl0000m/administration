package kz.bloooom.administration.converter.balance;

import kz.bloooom.administration.domain.dto.balance.BalanceInfoDto;
import kz.bloooom.administration.domain.entity.Balances;
import org.springframework.stereotype.Component;

@Component
public class BalanceInfoDtoConverter {

    public BalanceInfoDto convert(Balances source) {
        BalanceInfoDto target = new BalanceInfoDto();

        target.setUserId(source.getUser().getId());
        target.setUserName(source.getUser().getName());
        target.setCurrentBalance(source.getCurrentBalance());
        target.setCurrency(source.getCurrency().getTitle());
        return target;
    }
}
