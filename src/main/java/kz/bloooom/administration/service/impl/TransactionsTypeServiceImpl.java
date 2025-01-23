package kz.bloooom.administration.service.impl;

import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.domain.entity.TransactionsType;
import kz.bloooom.administration.exception.BloomAdministrationException;
import kz.bloooom.administration.repository.TransactionsTypeRepository;
import kz.bloooom.administration.service.TransactionsTypeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TransactionsTypeServiceImpl implements TransactionsTypeService {
    TransactionsTypeRepository transactionsTypeRepository;

    @Override
    public TransactionsType getById(Long id) {
        return transactionsTypeRepository.findById(id)
                .orElseThrow(() -> new BloomAdministrationException(
                        HttpStatus.NOT_FOUND,
                        ErrorCodeConstant.TRANSACTION_TYPE_WITH_THIS_ID_DOEST_EXISTS,
                        "messages.exception.transaction-type-not-found", id));
    }

    @Override
    public List<TransactionsType> getAllTransactionsType() {
        return transactionsTypeRepository.findAll();
    }
}
