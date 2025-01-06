package kz.bloooom.administration.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bloooom.administration.domain.dto.season.SeasonDto;
import kz.bloooom.administration.enumeration.Season;
import kz.bloooom.administration.facade.SeasonFacade;
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
@RequestMapping("/v1/season")
@Tag(name = "Season API", description = "Методы для работы с сезонами")
@SecurityRequirement(name = "Bearer Authentication")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SeasonController {
    SeasonFacade seasonFacade;

    @GetMapping()
    @Operation(summary = "Получить список")
    @SecurityRequirement(name = "Bearer Authentication")
    public List<SeasonDto> getAllRoles() {
        return seasonFacade.getAllSeasons();
    }


    @GetMapping("/code/{code}")
    @Operation(summary = "Получить по коду")
    @SecurityRequirement(name = "Bearer Authentication")
    public SeasonDto getRoleByCode(@PathVariable Season code) {
        return seasonFacade.getSeasonByCode(code);
    }
}
