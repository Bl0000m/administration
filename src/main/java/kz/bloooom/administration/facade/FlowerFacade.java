package kz.bloooom.administration.facade;

import kz.bloooom.administration.domain.dto.flower.FlowerCreatDto;
import kz.bloooom.administration.domain.dto.flower.FlowerInfoDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FlowerFacade {
    void createFlower(FlowerCreatDto flowerCreatDto, MultipartFile photo);

    List<FlowerInfoDto> getAll();

    FlowerInfoDto getById(Long id);
}
