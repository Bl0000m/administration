package kz.bloooom.administration.facade;

import kz.bloooom.administration.domain.dto.AbstractEnumDto;
import kz.bloooom.administration.enumeration.transaction_type.TransactionsTypeCode;

import java.util.List;

public interface TransactionsTypeFacade {
    List<AbstractEnumDto<TransactionsTypeCode>> getAllTypes();

    AbstractEnumDto<TransactionsTypeCode> getTypeById(Long id);
}
