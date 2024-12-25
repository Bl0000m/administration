package kz.bloooom.administration.converter.additional_elements;

import kz.bloooom.administration.domain.dto.additional_elements.AdditionalElementsNameInfoDto;
import kz.bloooom.administration.domain.dto.flower_variety.FlowerVarietyNameInfoDto;
import kz.bloooom.administration.domain.entity.AdditionalElements;
import kz.bloooom.administration.domain.entity.FlowerVariety;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdditionalElementsNameInfoDtoConverter {
    public AdditionalElementsNameInfoDto convert(AdditionalElements source) {
        return AdditionalElementsNameInfoDto.builder()
                .id(source.getId())
                .name(source.getName())
                .build();
    }

    public List<AdditionalElementsNameInfoDto> convert(Set<AdditionalElements> sources) {
        return CollectionUtils.isEmpty(sources) ?
                Collections.emptyList() :
                sources.stream().map(this::convert).collect(Collectors.toList());
    }
}
