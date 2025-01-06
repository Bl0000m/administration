package kz.bloooom.administration.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bloooom.administration.domain.dto.steam_type.StemTypeDto;
import kz.bloooom.administration.enumeration.StemType;
import kz.bloooom.administration.facade.StemTypeFacade;
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
@RequestMapping("/v1/steam-type")
@Tag(name = "Steam Type API", description = "Методы для работы с типами")
@SecurityRequirement(name = "Bearer Authentication")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StemTypeController {
    StemTypeFacade stemTypeFacade;

    @GetMapping()
    @Operation(summary = "Получить список")
    @SecurityRequirement(name = "Bearer Authentication")
    public List<StemTypeDto> getAllStemTypes() {
        return stemTypeFacade.getAllStemTypes();
    }


    @GetMapping("/code/{code}")
    @Operation(summary = "Получить по коду")
    @SecurityRequirement(name = "Bearer Authentication")
    public StemTypeDto getRoleByCode(@PathVariable StemType code) {
        return stemTypeFacade.getStemTypeByCode(code);
    }
}
