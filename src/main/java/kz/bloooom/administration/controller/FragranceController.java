package kz.bloooom.administration.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bloooom.administration.domain.dto.AbstractEnumDto;
import kz.bloooom.administration.domain.dto.fragrance.FragranceDto;
import kz.bloooom.administration.enumeration.Fragrance;
import kz.bloooom.administration.enumeration.role.RoleCode;
import kz.bloooom.administration.facade.FragranceFacade;
import kz.bloooom.administration.facade.RoleFacade;
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
@RequestMapping("/v1/fragrance")
@Tag(name = "Fragrance API", description = "Методы для работы с ароматом")
@SecurityRequirement(name = "Bearer Authentication")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FragranceController {
    FragranceFacade fragranceFacade;

    @GetMapping()
    @Operation(summary = "Получить список всех ролей")
    @SecurityRequirement(name = "Bearer Authentication")
    public List<FragranceDto> getAllRoles() {
        return fragranceFacade.getAllFragrances();
    }


    @GetMapping("/code/{code}")
    @Operation(summary = "Получить роль по коду")
    @SecurityRequirement(name = "Bearer Authentication")
    public FragranceDto getRoleByCode(@PathVariable Fragrance code) {
        return fragranceFacade.getFragranceByCode(code);
    }
}
