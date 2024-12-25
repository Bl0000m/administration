package kz.bloooom.administration.converter.additional_elements;

import kz.bloooom.administration.domain.dto.additional_elements.AdditionalElementsInfoDto;
import kz.bloooom.administration.domain.entity.AdditionalElements;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdditionalElementsInfoDtoConverter {

    public AdditionalElementsInfoDto convert(AdditionalElements source) {
        AdditionalElementsInfoDto target = new AdditionalElementsInfoDto();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setExample(source.getExample());
        target.setUnitOfMeasurement(source.getUnitOfMeasurement());
        return target;
    }

    public List<AdditionalElementsInfoDto> convert(List<AdditionalElements> bouquets) {
        return CollectionUtils.isEmpty(bouquets) ?
                Collections.emptyList() :
                bouquets.stream().map(this::convert).collect(Collectors.toList());
    }

}
