package kz.bloooom.administration.converter.transaction_type;

import kz.bloooom.administration.converter.AbstractEnumDtoConverter;
import kz.bloooom.administration.domain.entity.TransactionsType;
import kz.bloooom.administration.enumeration.transaction_type.TransactionsTypeCode;
import org.springframework.stereotype.Component;

@Component
public class TransactionsTypeConverter
        extends AbstractEnumDtoConverter<TransactionsTypeCode, TransactionsType> {
}
