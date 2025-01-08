package kz.bloooom.administration.facade.impl;

import kz.bloooom.administration.converter.currency.CurrencyConverter;
import kz.bloooom.administration.domain.dto.currency.CurrencyDto;
import kz.bloooom.administration.enumeration.Currency;
import kz.bloooom.administration.facade.CurrencyFacade;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CurrencyFacadeImpl implements CurrencyFacade {

    CurrencyConverter currencyConverter;

    @Override
    public List<CurrencyDto> getAllCurrencies() {
        List<Currency> currencies = new ArrayList<>();
        currencies.add(Currency.KZT);
        currencies.add(Currency.USD);
        currencies.add(Currency.EUR);
        return currencyConverter.convert(currencies);
    }

    @Override
    public CurrencyDto getCurrencyByCode(Currency code) {
        return currencyConverter.convert(code);
    }
}
