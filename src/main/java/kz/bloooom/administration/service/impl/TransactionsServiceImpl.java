package kz.bloooom.administration.service.impl;

import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.domain.entity.Transactions;
import kz.bloooom.administration.exception.BloomAdministrationException;
import kz.bloooom.administration.repository.TransactionsRepository;
import kz.bloooom.administration.service.TransactionsService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TransactionsServiceImpl implements TransactionsService {
    TransactionsRepository transactionsRepository;

    @Override
    @Transactional
    public Transactions save(Transactions transactions) {
        return transactionsRepository.save(transactions);
    }

    @Override
    public Transactions findById(Long id) {
        return transactionsRepository.findById(id)
                .orElseThrow(() -> new BloomAdministrationException(
                        HttpStatus.NOT_FOUND,
                        ErrorCodeConstant.TRANSACTION_WITH_THIS_ID_DOEST_EXISTS,
                        "messages.exception.transaction-not-found", id));
    }
}
