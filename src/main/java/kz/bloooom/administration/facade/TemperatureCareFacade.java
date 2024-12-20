package kz.bloooom.administration.facade;

import kz.bloooom.administration.domain.dto.temperature_care.TemperatureCareCreateDto;
import kz.bloooom.administration.domain.dto.temperature_care.TemperatureCareInfoDto;

import java.util.List;

public interface TemperatureCareFacade {
    void create(TemperatureCareCreateDto dto);

    TemperatureCareInfoDto getById(Long id);

    List<TemperatureCareInfoDto> getAll();
}
