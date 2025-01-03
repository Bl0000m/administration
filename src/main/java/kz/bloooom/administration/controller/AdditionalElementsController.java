package kz.bloooom.administration.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bloooom.administration.domain.dto.additional_elements.AdditionalElementsCreateDto;
import kz.bloooom.administration.domain.dto.additional_elements.AdditionalElementsInfoDto;
import kz.bloooom.administration.facade.AdditionalElementsFacade;
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
@RequestMapping("/v1/additional-element")
@Tag(name = "Additional Elements API", description = "Методы для работы с доп-ми элементами")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdditionalElementsController {

    AdditionalElementsFacade additionalElementsFacade;

    @PostMapping()
    @Operation(summary = "Создание доп элементов")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Void> create(@Valid @RequestBody AdditionalElementsCreateDto dto) {
        additionalElementsFacade.create(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить доп элемент по id")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<AdditionalElementsInfoDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(additionalElementsFacade.getById(id));
    }

    @GetMapping()
    @Operation(summary = "Получить список  доп элементов")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<AdditionalElementsInfoDto>> getByAll() {
        return ResponseEntity.ok(additionalElementsFacade.getAll());
    }

}
