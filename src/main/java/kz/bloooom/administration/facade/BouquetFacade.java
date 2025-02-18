package kz.bloooom.administration.facade;

import kz.bloooom.administration.domain.dto.bouquet.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BouquetFacade {
    void createBouquet(BouquetCreateDto bouquetCreateDto,
                       List<MultipartFile> files);

    void addBranchToBouquet(BouquetAddBranchDto bouquetAddBranchDto);

    BouquetDetailInfoDto getById(Long id);

    List<BouquetBranchInfoDto> getAllByBranchId(Long branchId);

    List<BouquetInfoDto> getAll();

    void deletePrice(BouquetDeletePriceDto dto);
}
