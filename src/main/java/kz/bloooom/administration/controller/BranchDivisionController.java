package kz.bloooom.administration.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bloooom.administration.domain.dto.branch_division.BranchDivisionCompanyInfoDto;
import kz.bloooom.administration.domain.dto.branch_division.BranchDivisionCreateDto;
import kz.bloooom.administration.domain.dto.branch_division.BranchDivisionShortDto;
import kz.bloooom.administration.facade.BranchDivisionFacade;
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
@RequestMapping("/v1/branch-division")
@Tag(name = "Branch Division API", description = "Методы для работы с филиалами компании")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BranchDivisionController {
    BranchDivisionFacade branchDivisionFacade;

    @PostMapping
    @Operation(summary = "Создать филиал")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Void> create(@Valid @RequestBody BranchDivisionCreateDto dto) {
        branchDivisionFacade.create(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить филиал по id")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<BranchDivisionCompanyInfoDto> getBranchDivisionById(@PathVariable Long id) {
        return ResponseEntity.ok(branchDivisionFacade.getBranchById(id));
    }

    @GetMapping("/company/{companyId}")
    @Operation(summary = "Получить список всех филиалов компании по id компании")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<BranchDivisionShortDto>> getBranchDivisiones(@PathVariable Long companyId) {
        return ResponseEntity.ok(branchDivisionFacade.getAllBranchDivisionByCompanyId(companyId));
    }
}
