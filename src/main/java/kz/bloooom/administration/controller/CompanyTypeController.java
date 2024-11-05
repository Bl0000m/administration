package kz.bloooom.administration.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bloooom.administration.domain.dto.AbstractEnumDto;
import kz.bloooom.administration.enumeration.company_type.CompanyTypeCode;
import kz.bloooom.administration.facade.CompanyTypeFacade;
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
@RequestMapping("/v1/company-type")
@Tag(name = "Company Type API", description = "Методы для работы с типами компании")
@SecurityRequirement(name = "Bearer Authentication")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CompanyTypeController {

    CompanyTypeFacade companyTypeFacade;

    @GetMapping()
    @Operation(summary = "Получить список всех типов компании")
    public List<AbstractEnumDto<CompanyTypeCode>> getAllCompanyTypes() {
        return companyTypeFacade.getAllCompanyTypes();
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Получить тип компани по id")
    public AbstractEnumDto<CompanyTypeCode> getCompanyTypeById(@PathVariable Long id) {
        return companyTypeFacade.getCompanyTypeById(id);
    }

    @GetMapping("/code/{code}")
    @Operation(summary = "Получить тип компани по коду")
    public AbstractEnumDto<CompanyTypeCode> getCompanyTypeByCode(@PathVariable CompanyTypeCode code) {
        return companyTypeFacade.getCompanyTypeByCode(code);
    }

}
