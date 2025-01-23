package kz.bloooom.administration.facade.impl;

import kz.bloooom.administration.converter.transaction_type.TransactionsTypeConverter;
import kz.bloooom.administration.domain.dto.AbstractEnumDto;
import kz.bloooom.administration.enumeration.transaction_type.TransactionsTypeCode;
import kz.bloooom.administration.facade.TransactionsTypeFacade;
import kz.bloooom.administration.service.TransactionsTypeService;
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
public class TransactionsTypeFacadeImpl implements TransactionsTypeFacade {
    TransactionsTypeService transactionsTypeService;
    TransactionsTypeConverter transactionsTypeConverter;

    @Override
    public List<AbstractEnumDto<TransactionsTypeCode>> getAllTypes() {
        return transactionsTypeConverter.convert(transactionsTypeService.getAllTransactionsType());
    }

    @Override
    public AbstractEnumDto<TransactionsTypeCode> getTypeById(Long id) {
        return transactionsTypeConverter.convert(transactionsTypeService.getById(id));
    }
}
