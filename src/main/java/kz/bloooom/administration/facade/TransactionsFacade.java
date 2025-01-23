package kz.bloooom.administration.facade;

import kz.bloooom.administration.domain.dto.transaction.TransactionCreateDto;
import kz.bloooom.administration.domain.dto.transaction.TransactionInfoDto;

public interface TransactionsFacade {
    void create(TransactionCreateDto dto);

    TransactionInfoDto getById(Long id);
}
