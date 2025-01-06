package kz.bloooom.administration.facade;

import kz.bloooom.administration.domain.dto.fragrance.FragranceDto;
import kz.bloooom.administration.enumeration.Fragrance;

import java.util.List;

public interface FragranceFacade {
    List<FragranceDto> getAllFragrances();

    FragranceDto getFragranceByCode(Fragrance code);
}
