package kz.bloooom.administration.repository;

import kz.bloooom.administration.domain.entity.CompanyType;
import kz.bloooom.administration.enumeration.company_type.CompanyTypeCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyTypeRepository extends JpaRepository<CompanyType, Long> {
    Optional<CompanyType> getByCode(CompanyTypeCode companyTypeCode);
}
