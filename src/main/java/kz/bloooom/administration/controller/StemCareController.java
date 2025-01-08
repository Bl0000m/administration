package kz.bloooom.administration.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bloooom.administration.domain.dto.stem_care.StemCareCreateDto;
import kz.bloooom.administration.domain.dto.stem_care.StemCareInfoDto;
import kz.bloooom.administration.facade.StemCareFacade;
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
@RequestMapping("/v1/stem-care")
@Tag(name = "Stem Care API", description = "Методы для работы с уходом за стволом")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StemCareController {
    StemCareFacade stemCareFacade;

    @PostMapping()
    @Operation(summary = "Создание ухода")
    @PreAuthorize("@keycloak.hasAnyRole('SUPER_ADMIN', 'FLORIST')")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Void> create(@Valid @RequestBody StemCareCreateDto dto) {
        stemCareFacade.create(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить уход по id")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<StemCareInfoDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(stemCareFacade.getById(id));
    }

    @GetMapping()
    @Operation(summary = "Получить список ухода букета")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<StemCareInfoDto>> getByAll() {
        return ResponseEntity.ok(stemCareFacade.getAll());
    }
}
