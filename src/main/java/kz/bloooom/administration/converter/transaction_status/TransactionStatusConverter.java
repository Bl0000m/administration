package kz.bloooom.administration.converter.transaction_status;

import kz.bloooom.administration.converter.AbstractEnumDtoConverter;
import kz.bloooom.administration.domain.entity.TransactionsStatus;
import kz.bloooom.administration.enumeration.transaction_status.TransactionsStatusCode;
import org.springframework.stereotype.Component;

@Component
public class TransactionStatusConverter
        extends AbstractEnumDtoConverter<TransactionsStatusCode, TransactionsStatus> {
}
