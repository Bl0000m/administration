package kz.bloooom.administration.facade.impl;

import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.converter.transaction.TransactionCreateDtoConverter;
import kz.bloooom.administration.converter.transaction.TransactionInfoDtoConverter;
import kz.bloooom.administration.domain.dto.transaction.TransactionCreateDto;
import kz.bloooom.administration.domain.dto.transaction.TransactionInfoDto;
import kz.bloooom.administration.domain.entity.Balances;
import kz.bloooom.administration.domain.entity.TransactionsType;
import kz.bloooom.administration.enumeration.transaction_type.TransactionsTypeCode;
import kz.bloooom.administration.exception.BloomAdministrationException;
import kz.bloooom.administration.facade.TransactionsFacade;
import kz.bloooom.administration.service.BalancesService;
import kz.bloooom.administration.service.TransactionsService;
import kz.bloooom.administration.service.TransactionsTypeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TransactionsFacadeImpl implements TransactionsFacade {
    TransactionsService transactionsService;
    BalancesService balancesService;
    TransactionsTypeService transactionsTypeService;
    TransactionInfoDtoConverter transactionInfoDtoConverter;
    TransactionCreateDtoConverter transactionCreateDtoConverter;

    @Override
    @Transactional
    public void create(TransactionCreateDto dto) {
        TransactionsType type = transactionsTypeService.getById(dto.getTypeId());
        Balances balances = balancesService.getBalanceByUserId(dto.getUserId());

        double updatedBalance = calculateUpdatedBalance(type.getCode(), balances.getCurrentBalance(), dto.getAmount());

        balances.setCurrentBalance(updatedBalance);
        balancesService.save(balances);

        transactionsService.save(transactionCreateDtoConverter.convert(dto));
    }

    private double calculateUpdatedBalance(TransactionsTypeCode typeCode, double currentBalance, double amount) {
        switch (typeCode) {
            case DEBIT:
            case TRANSFER:
                if (currentBalance < amount) {
                    throw new BloomAdministrationException(
                            HttpStatus.NOT_FOUND,
                            ErrorCodeConstant.NOT_ENOUGH_MONEY,
                            "messages.exception.not-enough-money"
                    );
                }
                return currentBalance - amount;

            case REPLENISHMENT:
                return currentBalance + amount;

            default:
                throw new IllegalArgumentException("Unknown transaction type: " + typeCode);
        }
    }

    @Override
    public TransactionInfoDto getById(Long id) {
        return transactionInfoDtoConverter.convert(transactionsService.findById(id));
    }
}
