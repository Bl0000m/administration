package kz.bloooom.administration.facade.impl;

import kz.bloooom.administration.converter.company.CompanyInfoDtoConverter;
import kz.bloooom.administration.converter.company.CreateCompanyDtoConverter;
import kz.bloooom.administration.domain.dto.company.CompanyCreateDto;
import kz.bloooom.administration.domain.dto.company.CompanyInfoDto;
import kz.bloooom.administration.domain.dto.company.CompanySearchDto;
import kz.bloooom.administration.domain.dto.page.PageDTO;
import kz.bloooom.administration.domain.entity.Company;
import kz.bloooom.administration.facade.CompanyFacade;
import kz.bloooom.administration.service.CompanyService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CompanyFacadeImpl implements CompanyFacade {

    CompanyService companyService;
    CreateCompanyDtoConverter createCompanyDtoConverter;
    CompanyInfoDtoConverter companyInfoDtoConverter;

    @Override
    @Transactional
    public void create(CompanyCreateDto dto) {
        Company company = createCompanyDtoConverter.convert(dto);
        company = companyService.save(company);
        log.info("Company: {} created", company.getName());
    }

    @Override
    public CompanyInfoDto getCompanyById(Long companyId) {
        Company company = companyService.getById(companyId);
        return companyInfoDtoConverter.convert(company);
    }

    @Override
    public PageDTO<CompanyInfoDto> getCompanies(CompanySearchDto companySearchDto,
                                                Pageable pageable) {
        return companyInfoDtoConverter.convert(companyService.getAllBySpecification(companySearchDto, pageable));
    }
}
