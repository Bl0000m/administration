package kz.bloooom.administration.service.impl;

import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.domain.entity.CompanyType;
import kz.bloooom.administration.enumeration.company_type.CompanyTypeCode;
import kz.bloooom.administration.exception.BloomAdministrationException;
import kz.bloooom.administration.repository.CompanyTypeRepository;
import kz.bloooom.administration.service.CompanyTypeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CompanyTypeServiceImpl implements CompanyTypeService {

    CompanyTypeRepository companyTypeRepository;

    @Override
    public CompanyType getById(Long id) {
        return companyTypeRepository.findById(id)
                .orElseThrow(() -> new BloomAdministrationException(
                        HttpStatus.NOT_FOUND,
                        ErrorCodeConstant.COMPANY_TYPE_WITH_THIS_ID_DOEST_EXISTS,
                        "messages.exception.company-type-not-found", id));
    }

    @Override
    public List<CompanyType> getAllCompanyTypes() {
        return companyTypeRepository.findAll();
    }

    @Override
    public CompanyType getByCode(CompanyTypeCode code) {
        return companyTypeRepository.getByCode(code)
                .orElseThrow(() -> new BloomAdministrationException(
                        HttpStatus.NOT_FOUND,
                        ErrorCodeConstant.COMPANY_TYPE_THIS_CODE_DOEST_EXISTS,
                        "messages.exception.company-type-code-not-found", code));
    }
}
