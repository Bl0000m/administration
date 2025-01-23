package kz.bloooom.administration.service;

import kz.bloooom.administration.domain.entity.TransactionsType;

import java.util.List;

public interface TransactionsTypeService {
    TransactionsType getById(Long id);

    List<TransactionsType> getAllTransactionsType();
}
