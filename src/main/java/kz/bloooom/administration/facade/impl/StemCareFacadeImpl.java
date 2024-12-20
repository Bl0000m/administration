package kz.bloooom.administration.facade.impl;

import kz.bloooom.administration.converter.stem_care.StemCareCreateDtoConverter;
import kz.bloooom.administration.converter.stem_care.StemCareInfoDtoConverter;
import kz.bloooom.administration.domain.dto.stem_care.StemCareCreateDto;
import kz.bloooom.administration.domain.dto.stem_care.StemCareInfoDto;
import kz.bloooom.administration.facade.StemCareFacade;
import kz.bloooom.administration.service.StemCareService;
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
public class StemCareFacadeImpl implements StemCareFacade {
    StemCareService stemCareService;
    StemCareCreateDtoConverter stemCareCreateDtoConverter;
    StemCareInfoDtoConverter stemCareInfoDtoConverter;

    @Override
    @Transactional
    public void create(StemCareCreateDto dto) {
        stemCareService.save(stemCareCreateDtoConverter.convert(dto));
    }

    @Override
    public StemCareInfoDto getById(Long id) {
        return stemCareInfoDtoConverter.convert(stemCareService.getById(id));
    }

    @Override
    public List<StemCareInfoDto> getAll() {
        return stemCareInfoDtoConverter.convert(stemCareService.getAll());
    }
}
