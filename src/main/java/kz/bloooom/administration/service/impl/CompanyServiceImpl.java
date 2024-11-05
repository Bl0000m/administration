package kz.bloooom.administration.service.impl;

import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.domain.dto.company.CompanySearchDto;
import kz.bloooom.administration.domain.entity.Company;
import kz.bloooom.administration.exception.BloomAdministrationException;
import kz.bloooom.administration.repository.CompanyRepository;
import kz.bloooom.administration.service.CompanyService;
import kz.bloooom.administration.util.SpecCompany;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CompanyServiceImpl implements CompanyService {

    CompanyRepository companyRepository;

    @Override
    @Transactional
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company getById(Long companyId) {
        return companyRepository.findById(companyId)
                .orElseThrow(() -> new BloomAdministrationException(
                        HttpStatus.NOT_FOUND,
                        ErrorCodeConstant.COMPANY_WITH_THIS_ID_DOEST_EXISTS,
                        "messages.exception.company-not-found", companyId));
    }

    @Override
    public Page<Company> getAllBySpecification(CompanySearchDto companySearchDto,
                                               Pageable pageable) {

        Specification<Company> resultSpec = SpecCompany.queryCompanySearch(companySearchDto.getQuery());
        return companyRepository.findAll(resultSpec, pageable);
    }
}
