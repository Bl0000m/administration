package kz.bloooom.administration.converter.temperature_care;

import kz.bloooom.administration.domain.dto.temperature_care.TemperatureCareInfoDto;
import kz.bloooom.administration.domain.entity.TemperatureCare;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TemperatureCareInfoDtoConverter {
    public TemperatureCareInfoDto convert(TemperatureCare source) {
        return TemperatureCareInfoDto.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .build();
    }

    public List<TemperatureCareInfoDto> convert(List<TemperatureCare> sources) {
        return CollectionUtils.isEmpty(sources) ?
                Collections.emptyList() :
                sources.stream().map(this::convert).collect(Collectors.toList());
    }
}
