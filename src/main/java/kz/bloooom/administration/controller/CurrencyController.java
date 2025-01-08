package kz.bloooom.administration.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bloooom.administration.domain.dto.currency.CurrencyDto;
import kz.bloooom.administration.enumeration.Currency;
import kz.bloooom.administration.facade.CurrencyFacade;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/currency")
@Tag(name = "Currency API", description = "Методы для работы с валютой")
@SecurityRequirement(name = "Bearer Authentication")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CurrencyController {
    CurrencyFacade currencyFacade;

    @GetMapping()
    @Operation(summary = "Получить список")
    @SecurityRequirement(name = "Bearer Authentication")
    public List<CurrencyDto> getAllRoles() {
        return currencyFacade.getAllCurrencies();
    }


    @GetMapping("/code/{code}")
    @Operation(summary = "Получить по коду")
    @SecurityRequirement(name = "Bearer Authentication")
    public CurrencyDto getRoleByCode(@PathVariable Currency code) {
        return currencyFacade.getCurrencyByCode(code);
    }
}
