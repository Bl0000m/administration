package kz.bloooom.administration.converter.bouquet;

import kz.bloooom.administration.converter.additional_elements.AdditionalElementsNameInfoDtoConverter;
import kz.bloooom.administration.converter.flower_variety.FlowerVarietyNameInfoDtoConverter;
import kz.bloooom.administration.converter.img.ImageInfoConverter;
import kz.bloooom.administration.domain.dto.bouquet.BouquetDetailInfoDto;
import kz.bloooom.administration.domain.dto.bouquet.BouquetInfoDto;
import kz.bloooom.administration.domain.entity.Bouquet;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BouquetDetailInfoDtoConverter {

    ImageInfoConverter imageInfoConverter;
    FlowerVarietyNameInfoDtoConverter flowerVarietyNameInfoDtoConverter;
    AdditionalElementsNameInfoDtoConverter additionalElementsNameInfoDtoConverter;

    public BouquetDetailInfoDto convert(Bouquet source) {
        BouquetDetailInfoDto target = new BouquetDetailInfoDto();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setCompanyName(source.getCompany().getName());
        target.setBouquetPhotos(imageInfoConverter.convert(source.getBouquetPhotos()));
        target.setPrice(source.getPrice());
        target.setBouquetStyle(source.getBouquetStyle().getName());
        target.setFlowerVarietyInfo(flowerVarietyNameInfoDtoConverter.convert(source.getFlowers()));
        target.setAdditionalElements(additionalElementsNameInfoDtoConverter.convert(source.getAdditionalElements()));
        return target;
    }
}
