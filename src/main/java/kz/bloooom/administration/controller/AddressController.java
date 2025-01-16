package kz.bloooom.administration.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bloooom.administration.domain.dto.address.OrderAddressCreateDto;
import kz.bloooom.administration.domain.dto.address.UserAddressCreateDto;
import kz.bloooom.administration.facade.AddressFacade;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/address")
@Tag(name = "Address API", description = "Методы для работы с адресами")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AddressController {
    AddressFacade addressFacade;

    @PostMapping("/user")
    @Operation(summary = "Создать user адрес")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Void> create(@Valid @RequestBody UserAddressCreateDto dto) {
        addressFacade.createUserAddress(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/order")
    @Operation(summary = "Создать order адрес")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Void> create(@Valid @RequestBody OrderAddressCreateDto dto) {
        addressFacade.createOrderAddress(dto);
        return ResponseEntity.ok().build();
    }
}
