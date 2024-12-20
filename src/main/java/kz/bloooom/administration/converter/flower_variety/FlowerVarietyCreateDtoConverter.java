package kz.bloooom.administration.converter.flower_variety;

import kz.bloooom.administration.domain.dto.flower_variety.FlowerVarietyCreateDto;
import kz.bloooom.administration.domain.entity.FlowerVariety;
import kz.bloooom.administration.service.*;
import kz.bloooom.administration.util.JwtUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FlowerVarietyCreateDtoConverter {
    FlowerService flowerService;
    StemCareService stemCareService;
    WaterCareService waterCareService;
    CountryService countryService;
    TemperatureCareService temperatureCareService;

    public FlowerVariety convert(FlowerVarietyCreateDto dto) {
        return FlowerVariety.builder()
                .name(dto.getName())
                .flower(flowerService.getById(dto.getFlowerId()))
                .shelfLifeDaysMin(dto.getShelfLifeDaysMin())
                .shelfLifeDaysMax(dto.getShelfLifeDaysMax())
                .fragrance(dto.getFragranceCode())
                .season(dto.getSeason())
                .steamType(dto.getSteamType())
                .budSizeMin(dto.getBudSizeMin())
                .budSizeMax(dto.getBudSizeMax())
                .stemHeightSizeMin(dto.getStemHeightSizeMin())
                .stemHeightSizeMax(dto.getStemHeightSizeMax())
                .stemCare(stemCareService.getById(dto.getStemCareId()))
                .waterCare(waterCareService.getById(dto.getWaterCareId()))
                .country(countryService.getById(dto.getCountryId()))
                .temperatureCare(temperatureCareService.getById(dto.getTemperatureCareId()))
                .createdDate(new Timestamp(System.currentTimeMillis()))
                .updatedDate(new Timestamp(System.currentTimeMillis()))
                .createdBy(JwtUtils.getKeycloakId())
                .updatedBy(JwtUtils.getKeycloakId())
                .build();
    }
}
