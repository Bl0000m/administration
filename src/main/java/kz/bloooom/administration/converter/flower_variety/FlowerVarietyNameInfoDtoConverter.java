package kz.bloooom.administration.converter.flower_variety;

import kz.bloooom.administration.domain.dto.flower_variety.FlowerVarietyInfoDto;
import kz.bloooom.administration.domain.dto.flower_variety.FlowerVarietyNameInfoDto;
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
public class FlowerVarietyNameInfoDtoConverter {
    public FlowerVarietyNameInfoDto convert(FlowerVariety source) {
        return FlowerVarietyNameInfoDto.builder()
                .id(source.getId())
                .name(source.getName())
                .image(source.getImage())
                .build();
    }

    public List<FlowerVarietyNameInfoDto> convert(Set<FlowerVariety> sources) {
        return CollectionUtils.isEmpty(sources) ?
                Collections.emptyList() :
                sources.stream().map(this::convert).collect(Collectors.toList());
    }
}
