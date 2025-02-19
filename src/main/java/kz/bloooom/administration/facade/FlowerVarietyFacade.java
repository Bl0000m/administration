package kz.bloooom.administration.facade;

import kz.bloooom.administration.domain.dto.flower_variety.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FlowerVarietyFacade {
    void create(FlowerVarietyCreateDto dto, MultipartFile photo);

    void addFlowerVarietyToBranch(FlowerVarietyAddBranchDto flowerVarietyAddBranchDto);

    void addFlowerVarietyPrice(FlowerVarietyAddBranchDto dto);

    FlowerVarietyInfoDto getById(Long id);

    void updatePrice(Long id, FlowerVarietyUpdateDto dto);

    List<FlowerVarietyPriceInfoDto> getByBranchIdAndVarietyId(Long branchId, Long varietyId);

    List<FlowerVarietyBranchInfoDto> getAllByBranchId(Long branchId);

    List<FlowerVarietyInfoDto> getAll();

    void deletePrice(Long priceId);
}
