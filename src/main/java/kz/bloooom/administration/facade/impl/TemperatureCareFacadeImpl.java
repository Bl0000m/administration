package kz.bloooom.administration.facade.impl;

import kz.bloooom.administration.converter.temperature_care.TemperatureCareCreateDtoConverter;
import kz.bloooom.administration.converter.temperature_care.TemperatureCareInfoDtoConverter;
import kz.bloooom.administration.domain.dto.temperature_care.TemperatureCareCreateDto;
import kz.bloooom.administration.domain.dto.temperature_care.TemperatureCareInfoDto;
import kz.bloooom.administration.facade.TemperatureCareFacade;
import kz.bloooom.administration.service.TemperatureCareService;
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
public class TemperatureCareFacadeImpl implements TemperatureCareFacade {
    TemperatureCareService temperatureCareService;
    TemperatureCareInfoDtoConverter temperatureCareInfoDtoConverter;
    TemperatureCareCreateDtoConverter temperatureCareCreateDtoConverter;

    @Override
    @Transactional
    public void create(TemperatureCareCreateDto dto) {
        temperatureCareService.save(temperatureCareCreateDtoConverter.convert(dto));
    }

    @Override
    public TemperatureCareInfoDto getById(Long id) {
        return temperatureCareInfoDtoConverter.convert(temperatureCareService.getById(id));
    }

    @Override
    public List<TemperatureCareInfoDto> getAll() {
        return temperatureCareInfoDtoConverter.convert(temperatureCareService.getAll());
    }
}
