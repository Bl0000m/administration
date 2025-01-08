package kz.bloooom.administration.facade;

import kz.bloooom.administration.domain.dto.currency.CurrencyDto;
import kz.bloooom.administration.enumeration.Currency;

import java.util.List;

public interface CurrencyFacade {
    List<CurrencyDto> getAllCurrencies();

    CurrencyDto getCurrencyByCode(Currency code);
}
