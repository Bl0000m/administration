package kz.bloooom.administration.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bloooom.administration.domain.dto.flower_variety.*;
import kz.bloooom.administration.facade.FlowerVarietyFacade;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/flower-variety")
@Tag(name = "Flower Variety API", description = "Методы для работы с сортами цветовов")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FlowerVarietyController {
    FlowerVarietyFacade flowerVarietyFacade;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Создание сортов цветка")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("@keycloak.hasAnyRole('SUPER_ADMIN', 'FLORIST')")
    public ResponseEntity<Void> create(
            @Valid @RequestPart(name = "dto") FlowerVarietyCreateDto dto,
            @RequestParam(name = "file") MultipartFile file) {
        flowerVarietyFacade.create(dto, file);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add")
    @Operation(summary = "Присоединиться к существующему сорту цветка")
    @PreAuthorize("@keycloak.hasAnyRole('SUPER_ADMIN', 'FLORIST')")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Void> create(@Valid @RequestBody FlowerVarietyAddBranchDto flowerVarietyAddBranchDto) {
        flowerVarietyFacade.addFlowerVarietyToBranch(flowerVarietyAddBranchDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/change-price")
    @Operation(summary = "Поменять цену существующего цветка")
    @PreAuthorize("@keycloak.hasAnyRole('SUPER_ADMIN', 'FLORIST')")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Void> changePrice(@Valid @RequestBody FlowerVarietyAddBranchDto flowerVarietyAddBranchDto) {
        flowerVarietyFacade.addFlowerVarietyPrice(flowerVarietyAddBranchDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить сорт по id")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<FlowerVarietyInfoDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(flowerVarietyFacade.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePrice(
            @PathVariable Long id,
            @RequestBody FlowerVarietyUpdateDto dto) {
        flowerVarietyFacade.updatePrice(id, dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/branch/{branchId}/variety/{varietyId}")
    @Operation(summary = "Получить заказ по филиал Id и статус Id")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<FlowerVarietyPriceInfoDto>> getByBranchIdAndVarietyIdId(@PathVariable Long branchId,
                                                                                       @PathVariable Long varietyId) {
        return ResponseEntity.ok(flowerVarietyFacade.getByBranchIdAndVarietyId(branchId, varietyId));
    }


    @GetMapping("/branch/{branchId}")
    @Operation(summary = "Получить сорты указанного филиала по его id")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<FlowerVarietyBranchInfoDto>> getByBranchId(@PathVariable Long branchId) {
        return ResponseEntity.ok(flowerVarietyFacade.getAllByBranchId(branchId));
    }

    @GetMapping()
    @Operation(summary = "Получить список сортов")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<FlowerVarietyInfoDto>> getByAll() {
        return ResponseEntity.ok(flowerVarietyFacade.getAll());
    }
}
