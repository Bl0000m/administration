package kz.bloooom.administration.converter.additional_elements;

import kz.bloooom.administration.domain.dto.additional_elements.AdditionalElementsPriceInfoDto;
import kz.bloooom.administration.domain.entity.AdditionalElementsPrice;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class AdditionalElementPriceInfoDtoConverter {

    public AdditionalElementsPriceInfoDto convert(AdditionalElementsPrice source) {
        return AdditionalElementsPriceInfoDto.builder()
                .id(source.getAdditionalElements().getId())
                .price(source.getPrice())
                .currency(Objects.nonNull(source.getCurrency()) ? source.getCurrency().getTitle() : null)
                .validFrom(source.getValidFrom())
                .validTo(source.getValidTo())
                .build();
    }

    public List<AdditionalElementsPriceInfoDto> convert(List<AdditionalElementsPrice> additionalElementsPrices) {
        return CollectionUtils.isEmpty(additionalElementsPrices) ?
                Collections.emptyList() :
                additionalElementsPrices.stream().map(this::convert).collect(Collectors.toList());
    }
}
