package kz.bloooom.administration.facade.impl;

import kz.bloooom.administration.converter.company_type.CompanyTypeConverter;
import kz.bloooom.administration.domain.dto.AbstractEnumDto;
import kz.bloooom.administration.enumeration.company_type.CompanyTypeCode;
import kz.bloooom.administration.facade.CompanyTypeFacade;
import kz.bloooom.administration.service.CompanyTypeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CompanyTypeFacadeImpl implements CompanyTypeFacade {

    CompanyTypeService companyTypeService;
    CompanyTypeConverter companyTypeConverter;

    @Override
    public List<AbstractEnumDto<CompanyTypeCode>> getAllCompanyTypes() {
        return companyTypeConverter.convert(companyTypeService.getAllCompanyTypes());
    }

    @Override
    public AbstractEnumDto<CompanyTypeCode> getCompanyTypeById(Long id) {
        return companyTypeConverter.convert(companyTypeService.getById(id));
    }

    @Override
    public AbstractEnumDto<CompanyTypeCode> getCompanyTypeByCode(CompanyTypeCode code) {
        return companyTypeConverter.convert(companyTypeService.getByCode(code));
    }
}
