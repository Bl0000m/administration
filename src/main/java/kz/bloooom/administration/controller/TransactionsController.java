package kz.bloooom.administration.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bloooom.administration.domain.dto.transaction.TransactionCreateDto;
import kz.bloooom.administration.domain.dto.transaction.TransactionInfoDto;
import kz.bloooom.administration.facade.TransactionsFacade;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/transactions")
@Tag(name = "Transactions API", description = "Методы для работы с транзакциями")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TransactionsController {
    TransactionsFacade transactionsFacade;


    @PostMapping
    @Operation(summary = "Создание транзакции")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Void> create(@Valid @RequestBody TransactionCreateDto transactionCreateDto) {
        transactionsFacade.create(transactionCreateDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить транзакцию по его id")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<TransactionInfoDto> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionsFacade.getById(id));
    }
}
