package kz.bloooom.administration.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bloooom.administration.domain.dto.AbstractEnumDto;
import kz.bloooom.administration.enumeration.status.StatusCode;
import kz.bloooom.administration.facade.StatusFacade;
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
@RequestMapping("/v1/status")
@Tag(name = "Status API", description = "Методы для работы с статусами")
@SecurityRequirement(name = "Bearer Authentication")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StatusController {

    StatusFacade statusFacade;

    @GetMapping()
    @Operation(summary = "Получить список всех статусов")
    @SecurityRequirement(name = "Bearer Authentication")
    public List<AbstractEnumDto<StatusCode>> getAllStatuses() {
        return statusFacade.getAllStatuses();
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Получить статус по id")
    @SecurityRequirement(name = "Bearer Authentication")
    public AbstractEnumDto<StatusCode> getStatusById(@PathVariable Long id) {
        return statusFacade.getStatusById(id);
    }

    @GetMapping("/code/{code}")
    @Operation(summary = "Получить статус по коду")
    @SecurityRequirement(name = "Bearer Authentication")
    public AbstractEnumDto<StatusCode> getStatusByCode(@PathVariable StatusCode code) {
        return statusFacade.getStatusByCode(code);
    }

}
