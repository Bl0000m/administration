package kz.bloooom.administration.facade;

import kz.bloooom.administration.domain.dto.steam_type.StemTypeDto;
import kz.bloooom.administration.enumeration.StemType;

import java.util.List;

public interface StemTypeFacade {
    List<StemTypeDto> getAllStemTypes();

    StemTypeDto getStemTypeByCode(StemType code);
}
