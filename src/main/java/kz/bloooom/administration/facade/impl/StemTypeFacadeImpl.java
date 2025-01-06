package kz.bloooom.administration.facade.impl;

import kz.bloooom.administration.converter.stem_type.StemTypeConverter;
import kz.bloooom.administration.domain.dto.steam_type.StemTypeDto;
import kz.bloooom.administration.enumeration.StemType;
import kz.bloooom.administration.facade.StemTypeFacade;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StemTypeFacadeImpl implements StemTypeFacade {
    StemTypeConverter stemTypeConverter;


    @Override
    public List<StemTypeDto> getAllStemTypes() {
        List<StemType> list = new ArrayList<>();
        list.add(StemType.HARD);
        list.add(StemType.SOFT);
        return stemTypeConverter.convert(list);
    }

    @Override
    public StemTypeDto getStemTypeByCode(StemType code) {
        return stemTypeConverter.convert(code);
    }
}
