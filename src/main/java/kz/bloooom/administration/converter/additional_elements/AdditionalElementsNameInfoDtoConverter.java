package kz.bloooom.administration.converter.additional_elements;

import kz.bloooom.administration.domain.dto.additional_elements.AdditionalElementsNameInfoDto;
import kz.bloooom.administration.domain.entity.AdditionalElements;
import kz.bloooom.administration.domain.entity.BouquetAdditionalElements;
import kz.bloooom.administration.service.BouquetAdditionalElementsService;
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
    BouquetAdditionalElementsService bouquetAdditionalElementsService;

    public AdditionalElementsNameInfoDto convert(AdditionalElements source, Long bouquetId) {
        BouquetAdditionalElements bouquetAdditionalElements = bouquetAdditionalElementsService.getByAdditionalElementIdAndBouquetId(source.getId(), bouquetId);
        return AdditionalElementsNameInfoDto.builder()
                .id(source.getId())
                .name(source.getName())
                .color(bouquetAdditionalElements.getColor())
                .quantity(bouquetAdditionalElements.getQuantity())
                .build();
    }

    public List<AdditionalElementsNameInfoDto> convert(Set<AdditionalElements> sources, Long bouquetId) {
        return CollectionUtils.isEmpty(sources) ?
                Collections.emptyList() :
                sources.stream().
                        map(source -> convert(source, bouquetId))
                        .collect(Collectors.toList());
    }
}
