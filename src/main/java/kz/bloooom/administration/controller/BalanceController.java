package kz.bloooom.administration.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bloooom.administration.domain.dto.balance.BalanceInfoDto;
import kz.bloooom.administration.facade.BalancesFacade;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/balances")
@Tag(name = "Address API", description = "Методы для работы с балансом")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BalanceController {

    BalancesFacade balancesFacade;

    @GetMapping("/{userId}")
    @Operation(summary = "Получить баланс пользователя по его userId")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<BalanceInfoDto> getOrderById(@PathVariable Long userId) {
        return ResponseEntity.ok(balancesFacade.getBalanceByUserId(userId));
    }
}
