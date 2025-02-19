package kz.bloooom.administration.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bloooom.administration.domain.dto.additional_elements.*;
import kz.bloooom.administration.facade.AdditionalElementsFacade;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/additional-element")
@Tag(name = "Additional Elements API", description = "Методы для работы с доп-ми элементами")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdditionalElementsController {

    AdditionalElementsFacade additionalElementsFacade;

    @PostMapping()
    @Operation(summary = "Создание доп элементов")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("@keycloak.hasAnyRole('SUPER_ADMIN', 'FLORIST')")
    public ResponseEntity<Void> create(@Valid @RequestBody AdditionalElementsCreateDto dto) {
        additionalElementsFacade.create(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add")
    @Operation(summary = "Присоединиться к существующему доп элементу")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("@keycloak.hasAnyRole('SUPER_ADMIN', 'FLORIST')")
    public ResponseEntity<Void> create(@Valid @RequestBody AdditionalElementAddBranchDto additionalElementAddBranchDto) {
        additionalElementsFacade.addAdditionalElementToBranch(additionalElementAddBranchDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/change-price")
    @Operation(summary = "Поменять цену существующего доп элемента")
    @PreAuthorize("@keycloak.hasAnyRole('SUPER_ADMIN', 'FLORIST')")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Void> changePrice(@Valid @RequestBody AdditionalElementAddBranchDto additionalElementAddBranchDto) {
        additionalElementsFacade.addAdditionalElementPrice(additionalElementAddBranchDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/branch/{branchId}")
    @Operation(summary = "Получить доп элементы указанного филиала по его id")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<AdditionalElementsBranchInfoDto>> getByBranchId(@PathVariable Long branchId) {
        return ResponseEntity.ok(additionalElementsFacade.getAllByBranchId(branchId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить доп элемент по id")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<AdditionalElementsInfoDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(additionalElementsFacade.getById(id));
    }

    @GetMapping("/branch/{branchId}/element/{elementId}")
    @Operation(summary = "Получить заказ по филиал Id и статус Id")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<AdditionalElementsPriceInfoDto>> getByBranchIdAndElementId(
            @PathVariable Long branchId,
            @PathVariable Long elementId) {
        return ResponseEntity.ok(additionalElementsFacade.getByBranchIdAndElementId(branchId, elementId));
    }

    @GetMapping()
    @Operation(summary = "Получить список  доп элементов")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<AdditionalElementsInfoDto>> getByAll() {
        return ResponseEntity.ok(additionalElementsFacade.getAll());
    }

    @DeleteMapping("/delete-price/{priceId}")
    @Operation(summary = "Удалить цену доп элемента")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Void> deletePrice(@PathVariable Long priceId) {
        additionalElementsFacade.deletePrice(priceId);
        return ResponseEntity.ok().build();
    }
}
