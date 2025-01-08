package kz.bloooom.administration.service;

import kz.bloooom.administration.domain.entity.BranchDivision;

import java.util.List;

public interface BranchDivisionService {
    BranchDivision save(BranchDivision branchDivision);

    BranchDivision getById(Long branchDivisionId);

    List<BranchDivision> getAll();

    List<BranchDivision> getAllByCompanyId(Long companyId);

}
