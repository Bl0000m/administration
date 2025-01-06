package kz.bloooom.administration.facade;

import kz.bloooom.administration.domain.dto.season.SeasonDto;
import kz.bloooom.administration.enumeration.Season;

import java.util.List;

public interface SeasonFacade {
    List<SeasonDto> getAllSeasons();

    SeasonDto getSeasonByCode(Season code);
}
