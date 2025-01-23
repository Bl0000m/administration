package kz.bloooom.administration.service;

import kz.bloooom.administration.domain.entity.Transactions;

public interface TransactionsService {
    Transactions save(Transactions transactions);

    Transactions findById(Long id);
}
