package kz.bloooom.administration.service;

import kz.bloooom.administration.domain.dto.company.CompanySearchDto;
import kz.bloooom.administration.domain.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompanyService {

    Company save(Company company);
    Company getById(Long companyId);
    Page<Company> getAllBySpecification(CompanySearchDto companySearchDto, Pageable pageable);
}
