package kz.bloooom.administration.service;

import kz.bloooom.administration.domain.entity.TransactionsStatus;

import java.util.List;

public interface TransactionsStatusService {
    TransactionsStatus getById(Long id);

    List<TransactionsStatus> getAllTransactionsStatus();
}
