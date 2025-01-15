package kz.bloooom.administration.converter.additional_elements;

import kz.bloooom.administration.converter.branch_division.BranchDivisionInfoDtoConverter;
import kz.bloooom.administration.domain.dto.additional_elements.AdditionalElementsInfoDto;
import kz.bloooom.administration.domain.dto.branch_division.BranchDivisionInfoDto;
import kz.bloooom.administration.domain.entity.AdditionalElements;
import kz.bloooom.administration.domain.entity.AdditionalElementsPrice;
import kz.bloooom.administration.domain.entity.BranchDivision;
import kz.bloooom.administration.domain.entity.FlowerVarietyPrice;
import kz.bloooom.administration.service.AdditionalElementsPriceService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdditionalElementsInfoDtoConverter {

    AdditionalElementsPriceService additionalElementsPriceService;
    BranchDivisionInfoDtoConverter branchDivisionInfoDtoConverter;

    public AdditionalElementsInfoDto convert(AdditionalElements source) {
        AdditionalElementsInfoDto target = new AdditionalElementsInfoDto();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setDescription(source.getDescription());

        List<AdditionalElementsPrice> additionalElementsPrices =
                additionalElementsPriceService.getByElementId(source.getId());

        Map<AdditionalElementsPrice, BranchDivision> additionalElementsPriceMap =
                additionalElementsPrices.stream()
                        .filter(Objects::nonNull)
                        .filter(additionalElementPrice -> additionalElementPrice.getBranchDivision() != null)
                        .collect(Collectors.toMap(
                                additionalElementPrice -> additionalElementPrice,
                                AdditionalElementsPrice::getBranchDivision
                        ));

        List<BranchDivisionInfoDto> branchDivisionInfo = branchDivisionInfoDtoConverter.convertAdditionalElementPriceMap(additionalElementsPriceMap);

        target.setBranchDivisionInfo(branchDivisionInfo);

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
