package kz.bloooom.administration.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bloooom.administration.domain.dto.AbstractEnumDto;
import kz.bloooom.administration.enumeration.transaction_type.TransactionsTypeCode;
import kz.bloooom.administration.facade.TransactionsTypeFacade;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/transactions-type")
@Tag(name = "Transactions Type API", description = "Методы для работы с типами транзакции")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TransactionsTypeController {
    TransactionsTypeFacade transactionsTypeFacade;

    @GetMapping()
    @Operation(summary = "Получить список")
    @SecurityRequirement(name = "Bearer Authentication")
    public List<AbstractEnumDto<TransactionsTypeCode>> getAllTypes() {
        return transactionsTypeFacade.getAllTypes();
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Получить по id")
    @SecurityRequirement(name = "Bearer Authentication")
    public AbstractEnumDto<TransactionsTypeCode> getTypeById(@PathVariable Long id) {
        return transactionsTypeFacade.getTypeById(id);
    }

}
