package kz.bloooom.administration.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bloooom.administration.domain.dto.company.CompanyCreateDto;
import kz.bloooom.administration.domain.dto.company.CompanyInfoDto;
import kz.bloooom.administration.domain.dto.company.CompanySearchDto;
import kz.bloooom.administration.domain.dto.page.PageDTO;
import kz.bloooom.administration.domain.entity.Company;
import kz.bloooom.administration.facade.CompanyFacade;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/companies")
@Tag(name = "Company API", description = "Методы для работы с компаниями")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CompanyController {

    CompanyFacade companyFacade;

    @PostMapping
    @Operation(summary = "Создать компанию")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Void> create(@Valid @RequestBody CompanyCreateDto dto) {
        log.info("POST: /v1/companies to create company form web admin");
        companyFacade.create(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить компанию по id")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<CompanyInfoDto> getCompanyById(@PathVariable Long id) {
        return ResponseEntity.ok(companyFacade.getCompanyById(id));
    }

    @GetMapping
    @Operation(summary = "Получить список всех компании")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<PageDTO<CompanyInfoDto>> getCompanies(
            @Valid @ParameterObject CompanySearchDto companySearchDto,
            @PageableDefault(sort = {
                    Company.Fields.createdDate}, direction = Sort.Direction.DESC)
            @ParameterObject Pageable pageable) {
        return ResponseEntity.ok(companyFacade.getCompanies(companySearchDto, pageable));
    }

}
