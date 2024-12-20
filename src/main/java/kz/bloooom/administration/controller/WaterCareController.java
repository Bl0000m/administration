package kz.bloooom.administration.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bloooom.administration.domain.dto.water_care.WaterCareCreateDto;
import kz.bloooom.administration.domain.dto.water_care.WaterCareInfoDto;
import kz.bloooom.administration.facade.WaterCareFacade;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/water-care")
@Tag(name = "Water Care API", description = "Методы для работы с уходом за водой")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WaterCareController {
    WaterCareFacade waterCareFacade;

    @PostMapping()
    @Operation(summary = "Создание ухода")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Void> create(@Valid @RequestBody WaterCareCreateDto dto) {
        waterCareFacade.create(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить уход по id")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<WaterCareInfoDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(waterCareFacade.getById(id));
    }

    @GetMapping()
    @Operation(summary = "Получить список ухода букета")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<WaterCareInfoDto>> getByAll() {
        return ResponseEntity.ok(waterCareFacade.getAll());
    }
}
