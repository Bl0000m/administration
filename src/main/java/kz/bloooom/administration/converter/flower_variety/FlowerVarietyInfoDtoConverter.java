package kz.bloooom.administration.converter.flower_variety;

import kz.bloooom.administration.converter.branch_division.BranchDivisionInfoDtoConverter;
import kz.bloooom.administration.converter.country.CountryInfoDtoConverter;
import kz.bloooom.administration.converter.flower.FlowerInfoDtoConverter;
import kz.bloooom.administration.converter.stem_care.StemCareInfoDtoConverter;
import kz.bloooom.administration.converter.temperature_care.TemperatureCareInfoDtoConverter;
import kz.bloooom.administration.converter.water_care.WaterCareInfoDtoConverter;
import kz.bloooom.administration.domain.dto.branch_division.BranchDivisionInfoDto;
import kz.bloooom.administration.domain.dto.flower_variety.FlowerVarietyInfoDto;
import kz.bloooom.administration.domain.entity.BranchDivision;
import kz.bloooom.administration.domain.entity.FlowerVariety;
import kz.bloooom.administration.domain.entity.FlowerVarietyPrice;
import kz.bloooom.administration.service.FlowerVarietyPriceService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FlowerVarietyInfoDtoConverter {

    FlowerInfoDtoConverter flowerInfoDtoConverter;
    FragranceInfoDtoConverter fragranceInfoDtoConverter;
    SeasonInfoDtoConverter seasonInfoDtoConverter;
    StemTypeInfoDtoConverter stemTypeInfoDtoConverter;
    StemCareInfoDtoConverter stemCareInfoDtoConverter;
    WaterCareInfoDtoConverter waterCareInfoDtoConverter;
    TemperatureCareInfoDtoConverter temperatureCareInfoDtoConverter;
    CountryInfoDtoConverter countryInfoDtoConverter;

    FlowerVarietyPriceService flowerVarietyPriceService;
    BranchDivisionInfoDtoConverter branchDivisionInfoDtoConverter;

    public FlowerVarietyInfoDto convert(FlowerVariety source) {

        List<FlowerVarietyPrice> flowerVarietyPrices =
                flowerVarietyPriceService.getAllByFlowerVarietyId(source.getId());

        Map<FlowerVarietyPrice, BranchDivision> flowerVarietyPriceMap = flowerVarietyPrices.stream()
                .collect(Collectors.toMap(
                        flowerVarietyPrice -> flowerVarietyPrice,
                        FlowerVarietyPrice::getBranchDivision
                ));

        List<BranchDivisionInfoDto> branchDivisionInfo = branchDivisionInfoDtoConverter.convert(flowerVarietyPriceMap);


        return FlowerVarietyInfoDto.builder()
                .id(source.getId())
                .name(source.getName())
                .flowerInfo(flowerInfoDtoConverter.convert(source.getFlower()))
                .branchDivisionInfo(branchDivisionInfo)
                .shelfLifeDaysMin(source.getShelfLifeDaysMin())
                .shelfLifeDaysMax(source.getShelfLifeDaysMax())
                .fragranceInfo(fragranceInfoDtoConverter.convert(source.getFragrance()))
                .seasonInfo(seasonInfoDtoConverter.convert(source.getSeason()))
                .color(source.getColor())
                .steamTypeInfo(stemTypeInfoDtoConverter.convert(source.getSteamType()))
                .budSizeMin(source.getBudSizeMin())
                .budSizeMax(source.getBudSizeMax())
                .stemHeightSizeMin(source.getStemHeightSizeMin())
                .stemHeightSizeMax(source.getStemHeightSizeMax())
                .image(source.getImage())
                .stemCareInfo(stemCareInfoDtoConverter.convert(source.getStemCare()))
                .waterCareInfo(waterCareInfoDtoConverter.convert(source.getWaterCare()))
                .countryInfoDto(countryInfoDtoConverter.convert(source.getCountry()))
                .temperatureCareInfo(temperatureCareInfoDtoConverter.convert(source.getTemperatureCare()))
                .build();
    }

    public List<FlowerVarietyInfoDto> convert(List<FlowerVariety> sources) {
        return CollectionUtils.isEmpty(sources) ?
                Collections.emptyList() :
                sources.stream().map(this::convert).collect(Collectors.toList());
    }
}
