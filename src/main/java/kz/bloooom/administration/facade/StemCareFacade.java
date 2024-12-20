package kz.bloooom.administration.facade;

import kz.bloooom.administration.domain.dto.stem_care.StemCareCreateDto;
import kz.bloooom.administration.domain.dto.stem_care.StemCareInfoDto;

import java.util.List;

public interface StemCareFacade {
    void create(StemCareCreateDto dto);

    StemCareInfoDto getById(Long id);

    List<StemCareInfoDto> getAll();
}
