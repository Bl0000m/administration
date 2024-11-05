package kz.bloooom.administration.facade;

import kz.bloooom.administration.domain.dto.company.CompanyCreateDto;
import kz.bloooom.administration.domain.dto.company.CompanyInfoDto;
import kz.bloooom.administration.domain.dto.company.CompanySearchDto;
import kz.bloooom.administration.domain.dto.page.PageDTO;
import org.springframework.data.domain.Pageable;

public interface CompanyFacade {
    void create(CompanyCreateDto dto);

    CompanyInfoDto getCompanyById(Long companyId);

    PageDTO<CompanyInfoDto> getCompanies(CompanySearchDto companySearchDto, Pageable pageable);
}
