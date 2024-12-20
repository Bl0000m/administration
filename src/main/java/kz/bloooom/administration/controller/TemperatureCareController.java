package kz.bloooom.administration.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bloooom.administration.domain.dto.temperature_care.TemperatureCareCreateDto;
import kz.bloooom.administration.domain.dto.temperature_care.TemperatureCareInfoDto;
import kz.bloooom.administration.facade.TemperatureCareFacade;
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
@RequestMapping("/v1/temperature-care")
@Tag(name = "Temperature Style API", description = "Методы для работы с заботой о температуре")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TemperatureCareController {
    TemperatureCareFacade temperatureCareFacade;

    @PostMapping()
    @Operation(summary = "Создание ухода")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Void> create(@Valid @RequestBody TemperatureCareCreateDto dto) {
        temperatureCareFacade.create(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить уход по id")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<TemperatureCareInfoDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(temperatureCareFacade.getById(id));
    }

    @GetMapping()
    @Operation(summary = "Получить список ухода букета")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<TemperatureCareInfoDto>> getByAll() {
        return ResponseEntity.ok(temperatureCareFacade.getAll());
    }
}
