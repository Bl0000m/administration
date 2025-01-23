package kz.bloooom.administration.service.impl;

import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.domain.entity.TransactionsStatus;
import kz.bloooom.administration.exception.BloomAdministrationException;
import kz.bloooom.administration.repository.TransactionsStatusRepository;
import kz.bloooom.administration.service.TransactionsStatusService;
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
public class TransactionsStatusServiceImpl implements TransactionsStatusService {
    TransactionsStatusRepository transactionsStatusRepository;

    @Override
    public TransactionsStatus getById(Long id) {
        return transactionsStatusRepository.findById(id)
                .orElseThrow(() -> new BloomAdministrationException(
                        HttpStatus.NOT_FOUND,
                        ErrorCodeConstant.TRANSACTION_STATUS_WITH_THIS_ID_DOEST_EXISTS,
                        "messages.exception.transaction-status-not-found", id));
    }

    @Override
    public List<TransactionsStatus> getAllTransactionsStatus() {
        return transactionsStatusRepository.findAll();
    }
}
