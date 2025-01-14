package kz.bloooom.administration.converter.flower_variety;

import kz.bloooom.administration.domain.dto.flower_variety.FlowerVarietyNameInfoDto;
import kz.bloooom.administration.domain.entity.FlowerVariety;
import kz.bloooom.administration.service.BouquetFlowersService;
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
    BouquetFlowersService bouquetFlowersService;

    public FlowerVarietyNameInfoDto convert(FlowerVariety source, Long bouquetId) {
        return FlowerVarietyNameInfoDto.builder()
                .id(source.getId())
                .name(source.getName())
                .image(source.getImage())
                .quantity(bouquetFlowersService.getByFlowerVarietyIdAndBouquetId(source.getId(), bouquetId).getQuantity())
                .build();
    }

    public List<FlowerVarietyNameInfoDto> convert(Set<FlowerVariety> sources, Long bouquetId) {
        return CollectionUtils.isEmpty(sources) ?
                Collections.emptyList() :
                sources.stream().map(source -> convert(source, bouquetId)).collect(Collectors.toList());
    }
}
