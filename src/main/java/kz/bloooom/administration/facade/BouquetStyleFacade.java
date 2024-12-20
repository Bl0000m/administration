package kz.bloooom.administration.facade;

import kz.bloooom.administration.domain.dto.bouquet_style.BouquetStyleCreateDto;
import kz.bloooom.administration.domain.dto.bouquet_style.BouquetStyleInfoDto;

import java.util.List;

public interface BouquetStyleFacade {
    void create(BouquetStyleCreateDto dto);

    BouquetStyleInfoDto getById(Long id);

    List<BouquetStyleInfoDto> getAll();
}
