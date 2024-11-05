package kz.bloooom.administration.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bloooom.administration.domain.dto.AbstractEnumDto;
import kz.bloooom.administration.domain.dto.role.RoleDto;
import kz.bloooom.administration.enumeration.role.RoleCode;
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
@RequestMapping("/v1/roles")
@Tag(name = "Role API", description = "Методы для работы с ролями")
@SecurityRequirement(name = "Bearer Authentication")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {

    RoleFacade roleFacade;

    @GetMapping()
    @Operation(summary = "Получить список всех ролей")
    public List<AbstractEnumDto<RoleCode>> getAllRoles() {
        return roleFacade.getAllRoles();
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Получить роль по id")
    public AbstractEnumDto<RoleCode> getRoleById(@PathVariable Long id) {
        return roleFacade.getRoleById(id);
    }

    @GetMapping("/code/{code}")
    @Operation(summary = "Получить роль по коду")
    public AbstractEnumDto<RoleCode> getRoleByCode(@PathVariable RoleCode code) {
        return roleFacade.getRoleByCode(code);
    }

}
