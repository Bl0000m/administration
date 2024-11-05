package kz.bloooom.administration.service;

import kz.bloooom.administration.domain.entity.CompanyType;
import kz.bloooom.administration.enumeration.company_type.CompanyTypeCode;

import java.util.List;

public interface CompanyTypeService {
    CompanyType getById(Long id);

    List<CompanyType> getAllCompanyTypes();

    CompanyType getByCode(CompanyTypeCode code);
}
