package kz.bloooom.administration.facade;


import kz.bloooom.administration.domain.dto.AbstractEnumDto;
import kz.bloooom.administration.enumeration.company_type.CompanyTypeCode;

import java.util.List;

public interface CompanyTypeFacade {
    List<AbstractEnumDto<CompanyTypeCode>> getAllCompanyTypes();

    AbstractEnumDto<CompanyTypeCode> getCompanyTypeById(Long id);

    AbstractEnumDto<CompanyTypeCode> getCompanyTypeByCode(CompanyTypeCode code);
}
