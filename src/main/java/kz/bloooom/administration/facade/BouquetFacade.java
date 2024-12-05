package kz.bloooom.administration.facade;

import kz.bloooom.administration.domain.dto.bouquet.BouquetCreateDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BouquetFacade {
    void createBouquet(BouquetCreateDto bouquetCreateDto,
                       List<MultipartFile> files);
}
