package kz.bloooom.administration.facade;

import kz.bloooom.administration.domain.dto.water_care.WaterCareCreateDto;
import kz.bloooom.administration.domain.dto.water_care.WaterCareInfoDto;

import java.util.List;

public interface WaterCareFacade {
    void create(WaterCareCreateDto dto);

    WaterCareInfoDto getById(Long id);

    List<WaterCareInfoDto> getAll();
}
