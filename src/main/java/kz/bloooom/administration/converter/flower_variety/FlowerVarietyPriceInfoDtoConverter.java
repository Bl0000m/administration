package kz.bloooom.administration.converter.flower_variety;

import kz.bloooom.administration.domain.dto.flower_variety.FlowerVarietyPriceInfoDto;
import kz.bloooom.administration.domain.entity.FlowerVarietyPrice;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FlowerVarietyPriceInfoDtoConverter {

    public FlowerVarietyPriceInfoDto convert(FlowerVarietyPrice source) {
        return FlowerVarietyPriceInfoDto.builder()
                .id(source.getId())
                .price(source.getPrice())
                .currency(Objects.nonNull(source.getCurrency()) ? source.getCurrency().getTitle() : null)
                .validFrom(source.getValidFrom())
                .validTo(source.getValidTo())
                .build();
    }

    public List<FlowerVarietyPriceInfoDto> convert(List<FlowerVarietyPrice> sources) {
        return CollectionUtils.isEmpty(sources) ?
                Collections.emptyList() :
                sources.stream().map(this::convert).collect(Collectors.toList());
    }
}
