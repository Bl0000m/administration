package kz.bloooom.administration.converter.transaction;


import kz.bloooom.administration.domain.dto.transaction.TransactionCreateDto;
import kz.bloooom.administration.domain.entity.Transactions;
import kz.bloooom.administration.service.TransactionsStatusService;
import kz.bloooom.administration.service.TransactionsTypeService;
import kz.bloooom.administration.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TransactionCreateDtoConverter {

    UserService userService;
    TransactionsTypeService transactionsTypeService;
    TransactionsStatusService transactionsStatusService;

    public Transactions convert(TransactionCreateDto source) {
        Transactions target = new Transactions();
        target.setUser(userService.getById(source.getUserId()));
        target.setAmount(source.getAmount());
        target.setCurrency(source.getCurrency());
        target.setType(transactionsTypeService.getById(source.getTypeId()));
        target.setStatus(transactionsStatusService.getById(source.getStatusId()));
//        target.setDescription(); надо спросить как заполнять
        target.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        target.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
        return target;
    }
}
