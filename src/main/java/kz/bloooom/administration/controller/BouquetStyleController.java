package kz.bloooom.administration.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bloooom.administration.domain.dto.bouquet_style.BouquetStyleCreateDto;
import kz.bloooom.administration.domain.dto.bouquet_style.BouquetStyleInfoDto;
import kz.bloooom.administration.facade.BouquetStyleFacade;
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
@RequestMapping("/v1/bouquet-style")
@Tag(name = "Bouquet Style API", description = "Методы для работы с стилями букетов")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BouquetStyleController {
    BouquetStyleFacade bouquetStyleFacade;

    @PostMapping()
    @Operation(summary = "Создание стиля букета")
    @PreAuthorize("@keycloak.hasAnyRole('SUPER_ADMIN', 'FLORIST')")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Void> create(@Valid @RequestBody BouquetStyleCreateDto dto) {
        bouquetStyleFacade.create(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить стиль букета по id")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<BouquetStyleInfoDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(bouquetStyleFacade.getById(id));
    }

    @GetMapping()
    @Operation(summary = "Получить список стиль букета")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<BouquetStyleInfoDto>> getByAll() {
        return ResponseEntity.ok(bouquetStyleFacade.getAll());
    }
}
