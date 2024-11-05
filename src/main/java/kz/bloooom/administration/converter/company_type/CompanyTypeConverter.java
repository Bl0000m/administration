package kz.bloooom.administration.converter.company_type;


import kz.bloooom.administration.converter.AbstractEnumDtoConverter;
import kz.bloooom.administration.domain.entity.CompanyType;
import kz.bloooom.administration.enumeration.company_type.CompanyTypeCode;
import org.springframework.stereotype.Component;


@Component
public class CompanyTypeConverter
        extends AbstractEnumDtoConverter<CompanyTypeCode, CompanyType> {
}