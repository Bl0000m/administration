package kz.bloooom.administration.converter.water_care;

import kz.bloooom.administration.domain.dto.water_care.WaterCareInfoDto;
import kz.bloooom.administration.domain.entity.WaterCare;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WaterCareInfoDtoConverter {

    public WaterCareInfoDto convert(WaterCare source) {
        return WaterCareInfoDto.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .build();
    }

    public List<WaterCareInfoDto> convert(List<WaterCare> sources) {
        return CollectionUtils.isEmpty(sources) ?
                Collections.emptyList() :
                sources.stream().map(this::convert).collect(Collectors.toList());
    }
}
