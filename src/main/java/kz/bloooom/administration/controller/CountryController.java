package kz.bloooom.administration.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bloooom.administration.domain.dto.country.CountryCreateDto;
import kz.bloooom.administration.domain.dto.country.CountryInfoDto;
import kz.bloooom.administration.facade.CountryFacade;
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
@RequestMapping("/v1/country")
@Tag(name = "Country API", description = "Методы для работы с странами")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CountryController {
    CountryFacade countryFacade;

    @PostMapping()
    @Operation(summary = "Создание страны")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Void> create(@Valid @RequestBody CountryCreateDto dto) {
        countryFacade.create(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить страну по id")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<CountryInfoDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(countryFacade.getById(id));
    }

    @GetMapping()
    @Operation(summary = "Получить список стран")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<CountryInfoDto>> getByAll() {
        return ResponseEntity.ok(countryFacade.getAll());
    }
}
