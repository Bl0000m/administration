package kz.bloooom.administration.converter.currency;

import kz.bloooom.administration.domain.dto.currency.CurrencyDto;
import kz.bloooom.administration.enumeration.Currency;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CurrencyConverter {
    public CurrencyDto convert(Currency source) {
        return CurrencyDto.builder()
                .code(source)
                .name(source.getTitle())
                .build();
    }

    public List<CurrencyDto> convert(List<Currency> sources) {
        return CollectionUtils.isEmpty(sources) ?
                Collections.emptyList() :
                sources.stream().map(this::convert).collect(Collectors.toList());
    }
}
