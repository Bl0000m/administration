package kz.bloooom.administration.converter.flower;

import kz.bloooom.administration.domain.dto.flower.FlowerInfoDto;
import kz.bloooom.administration.domain.entity.Flower;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FlowerInfoDtoConverter {
    public FlowerInfoDto convert(Flower source) {
        return FlowerInfoDto.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .build();
    }

    public List<FlowerInfoDto> convert(List<Flower> sources) {
        return CollectionUtils.isEmpty(sources) ?
                Collections.emptyList() :
                sources.stream().map(this::convert).collect(Collectors.toList());
    }
}
