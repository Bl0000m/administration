package kz.bloooom.administration.facade.impl;

import kz.bloooom.administration.converter.season.SeasonConverter;
import kz.bloooom.administration.domain.dto.season.SeasonDto;
import kz.bloooom.administration.enumeration.Season;
import kz.bloooom.administration.facade.SeasonFacade;
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
public class SeasonFacadeImpl implements SeasonFacade {
    SeasonConverter seasonConverter;

    @Override
    public List<SeasonDto> getAllSeasons() {
        List<Season> list = new ArrayList<>();
        list.add(Season.AUTUMN);
        list.add(Season.SPRING);
        list.add(Season.SUMMER);
        list.add(Season.WINTER);
        return seasonConverter.convert(list);
    }

    @Override
    public SeasonDto getSeasonByCode(Season code) {
        return seasonConverter.convert(code);
    }
}
