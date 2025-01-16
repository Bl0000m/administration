package kz.bloooom.administration.facade;

import kz.bloooom.administration.domain.dto.branch_division.BranchDivisionCompanyInfoDto;
import kz.bloooom.administration.domain.dto.branch_division.BranchDivisionCreateDto;
import kz.bloooom.administration.domain.dto.branch_division.BranchDivisionInfoDto;
import kz.bloooom.administration.domain.dto.branch_division.BranchDivisionShortDto;

import java.util.List;

public interface BranchDivisionFacade {

    void create(BranchDivisionCreateDto dto);

    BranchDivisionCompanyInfoDto getBranchById(Long branchId);

    List<BranchDivisionShortDto> getAllBranchDivisionByCompanyId(Long companyId);
}
