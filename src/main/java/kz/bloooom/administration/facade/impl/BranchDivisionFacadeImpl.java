package kz.bloooom.administration.facade.impl;

import kz.bloooom.administration.converter.branch_division.BranchDivisionCreateDtoConverter;
import kz.bloooom.administration.converter.branch_division.BranchDivisionInfoDtoConverter;
import kz.bloooom.administration.domain.dto.branch_division.BranchDivisionCreateDto;
import kz.bloooom.administration.domain.dto.branch_division.BranchDivisionInfoDto;
import kz.bloooom.administration.facade.BranchDivisionFacade;
import kz.bloooom.administration.service.BranchDivisionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BranchDivisionFacadeImpl implements BranchDivisionFacade {
    BranchDivisionInfoDtoConverter branchDivisionInfoDtoConverter;
    BranchDivisionCreateDtoConverter branchDivisionCreateDtoConverter;
    BranchDivisionService branchDivisionService;

    @Override
    @Transactional
    public void create(BranchDivisionCreateDto dto) {
        branchDivisionService.save(branchDivisionCreateDtoConverter.convert(dto));
    }

    @Override
    public BranchDivisionInfoDto getBranchById(Long branchId) {
        return branchDivisionInfoDtoConverter.convert(branchDivisionService.getById(branchId));
    }

    @Override
    public List<BranchDivisionInfoDto> getAllBranchDivisionByCompanyId(Long companyId) {
        return branchDivisionInfoDtoConverter.convert(branchDivisionService.getAllByCompanyId(companyId));
    }
}
