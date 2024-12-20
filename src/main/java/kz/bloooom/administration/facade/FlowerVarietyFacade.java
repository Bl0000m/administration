package kz.bloooom.administration.facade;

import kz.bloooom.administration.domain.dto.flower_variety.FlowerVarietyCreateDto;
import kz.bloooom.administration.domain.dto.flower_variety.FlowerVarietyInfoDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FlowerVarietyFacade {
    void create(FlowerVarietyCreateDto dto, MultipartFile photo);

    FlowerVarietyInfoDto getById(Long id);

    List<FlowerVarietyInfoDto> getAll();
}
