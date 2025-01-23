package kz.bloooom.administration.facade;

import kz.bloooom.administration.domain.dto.AbstractEnumDto;
import kz.bloooom.administration.enumeration.transaction_status.TransactionsStatusCode;

import java.util.List;

public interface TransactionsStatusFacade {
    List<AbstractEnumDto<TransactionsStatusCode>> getAllStatuses();

    AbstractEnumDto<TransactionsStatusCode> getStatusById(Long id);

}
