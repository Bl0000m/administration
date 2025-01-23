package kz.bloooom.administration.facade.impl;

import kz.bloooom.administration.converter.transaction_status.TransactionStatusConverter;
import kz.bloooom.administration.domain.dto.AbstractEnumDto;
import kz.bloooom.administration.enumeration.transaction_status.TransactionsStatusCode;
import kz.bloooom.administration.facade.TransactionsStatusFacade;
import kz.bloooom.administration.service.TransactionsStatusService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TransactionsStatusFacadeImpl implements TransactionsStatusFacade {
    TransactionsStatusService transactionsStatusService;
    TransactionStatusConverter transactionStatusConverter;

    @Override
    public List<AbstractEnumDto<TransactionsStatusCode>> getAllStatuses() {
        return transactionStatusConverter.convert(transactionsStatusService.getAllTransactionsStatus());
    }

    @Override
    public AbstractEnumDto<TransactionsStatusCode> getStatusById(Long id) {
        return transactionStatusConverter.convert(transactionsStatusService.getById(id));
    }
}
