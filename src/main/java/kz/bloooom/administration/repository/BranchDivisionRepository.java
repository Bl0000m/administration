package kz.bloooom.administration.repository;

import kz.bloooom.administration.domain.entity.BranchDivision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchDivisionRepository extends JpaRepository<BranchDivision, Long> {

    List<BranchDivision> findAllByCompanyId(Long companyId);
}
