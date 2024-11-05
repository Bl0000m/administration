package kz.bloooom.administration.converter.company;


import kz.bloooom.administration.domain.dto.company.CompanyCreateDto;
import kz.bloooom.administration.domain.entity.Company;
import kz.bloooom.administration.service.CompanyTypeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CreateCompanyDtoConverter {

    CompanyTypeService companyTypeService;

    public Company convert(CompanyCreateDto source) {
        Company target = new Company();
        target.setBin(source.getBin());
        target.setName(source.getName());
        target.setCompanyType(companyTypeService.getById(source.getTypeId()));
        target.setAddress(source.getAddress());
        target.setAdditionalAddress(source.getAdditionalAddress());
        target.setPhoneNumber(source.getPhone());
        target.setEmail(source.getEmail());
        target.setWebsite(source.getWebsite());
        target.setSocialMedia(source.getSocialMedia());
        target.setContractID(UUID.randomUUID().toString().replace("-", ""));
        target.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        target.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
        target.setDeleted(false);
        return target;
    }
}
