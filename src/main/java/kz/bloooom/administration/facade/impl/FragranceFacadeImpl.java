package kz.bloooom.administration.facade.impl;

import kz.bloooom.administration.converter.fragrance.FragranceConverter;
import kz.bloooom.administration.domain.dto.fragrance.FragranceDto;
import kz.bloooom.administration.enumeration.Fragrance;
import kz.bloooom.administration.facade.FragranceFacade;
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
public class FragranceFacadeImpl implements FragranceFacade {
    FragranceConverter fragranceConverter;

    @Override
    public List<FragranceDto> getAllFragrances() {
        List<Fragrance> fragrances = new ArrayList<>();
        return fragranceConverter.convert(fragrances);
    }

    @Override
    public FragranceDto getFragranceByCode(Fragrance code) {
        return fragranceConverter.convert(code);
    }
}
