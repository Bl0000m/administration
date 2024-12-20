package kz.bloooom.administration.facade.impl;

import kz.bloooom.administration.converter.bouquet_style.BouquetStyleCreateDtoConverter;
import kz.bloooom.administration.converter.bouquet_style.BouquetStyleInfoDtoConverter;
import kz.bloooom.administration.domain.dto.bouquet_style.BouquetStyleCreateDto;
import kz.bloooom.administration.domain.dto.bouquet_style.BouquetStyleInfoDto;
import kz.bloooom.administration.facade.BouquetStyleFacade;
import kz.bloooom.administration.service.BouquetStyleService;
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
public class BouquetStyleFacadeImpl implements BouquetStyleFacade {
    BouquetStyleService bouquetStyleService;
    BouquetStyleInfoDtoConverter bouquetStyleInfoDtoConverter;
    BouquetStyleCreateDtoConverter bouquetStyleCreateDtoConverter;

    @Override
    @Transactional
    public void create(BouquetStyleCreateDto dto) {
        bouquetStyleService.save(bouquetStyleCreateDtoConverter.convert(dto));
    }

    @Override
    public BouquetStyleInfoDto getById(Long id) {
        return bouquetStyleInfoDtoConverter.convert(bouquetStyleService.getById(id));
    }

    @Override
    public List<BouquetStyleInfoDto> getAll() {
        return bouquetStyleInfoDtoConverter.convert(bouquetStyleService.getAll());
    }
}
