package kz.bloooom.administration.facade;

import kz.bloooom.administration.domain.dto.flower.FlowerCreatDto;
import org.springframework.web.multipart.MultipartFile;

public interface FlowerFacade {
    void createFlower(FlowerCreatDto flowerCreatDto, MultipartFile photo);
}
