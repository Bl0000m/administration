package kz.bloooom.administration.facade;

import kz.bloooom.administration.domain.dto.additional_elements.*;

import java.util.List;

public interface AdditionalElementsFacade {
    void create(AdditionalElementsCreateDto dto);

    void addAdditionalElementToBranch(AdditionalElementAddBranchDto additionalElementAddBranchDto);

    void addAdditionalElementPrice(AdditionalElementAddBranchDto additionalElementAddBranchDto);

    AdditionalElementsInfoDto getById(Long id);

    List<AdditionalElementsPriceInfoDto> getByBranchIdAndElementId(Long branchId, Long elementId);

    List<AdditionalElementsBranchInfoDto> getAllByBranchId(Long branchId);

    List<AdditionalElementsInfoDto> getAll();

    void deletePrice(AdditionalElementAddBranchDto dto);
}
