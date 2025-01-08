package kz.bloooom.administration.facade;

import kz.bloooom.administration.domain.dto.branch_division.BranchDivisionCreateDto;
import kz.bloooom.administration.domain.dto.branch_division.BranchDivisionInfoDto;

import java.util.List;

public interface BranchDivisionFacade {

    void create(BranchDivisionCreateDto dto);

    BranchDivisionInfoDto getBranchById(Long branchId);

    List<BranchDivisionInfoDto> getAllBranchDivisionByCompanyId(Long companyId);
}
