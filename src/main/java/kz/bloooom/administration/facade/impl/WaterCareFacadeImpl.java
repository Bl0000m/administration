package kz.bloooom.administration.facade.impl;

import kz.bloooom.administration.converter.water_care.WaterCareCreateDtoConverter;
import kz.bloooom.administration.converter.water_care.WaterCareInfoDtoConverter;
import kz.bloooom.administration.domain.dto.water_care.WaterCareCreateDto;
import kz.bloooom.administration.domain.dto.water_care.WaterCareInfoDto;
import kz.bloooom.administration.facade.WaterCareFacade;
import kz.bloooom.administration.service.WaterCareService;
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
public class WaterCareFacadeImpl implements WaterCareFacade {
    WaterCareService waterCareService;
    WaterCareInfoDtoConverter waterCareInfoDtoConverter;
    WaterCareCreateDtoConverter waterCareCreateDtoConverter;

    @Override
    @Transactional
    public void create(WaterCareCreateDto dto) {
        waterCareService.save(waterCareCreateDtoConverter.convert(dto));
    }

    @Override
    public WaterCareInfoDto getById(Long id) {
        return waterCareInfoDtoConverter.convert(waterCareService.getById(id));
    }

    @Override
    public List<WaterCareInfoDto> getAll() {
        return waterCareInfoDtoConverter.convert(waterCareService.getAll());
    }
}
