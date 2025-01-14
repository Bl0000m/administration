package kz.bloooom.administration.converter.bouquet;

import kz.bloooom.administration.converter.additional_elements.AdditionalElementsNameInfoDtoConverter;
import kz.bloooom.administration.converter.branch_division.BranchBouquetInfoDtoConverter;
import kz.bloooom.administration.converter.flower_variety.FlowerVarietyNameInfoDtoConverter;
import kz.bloooom.administration.converter.img.ImageInfoConverter;
import kz.bloooom.administration.domain.dto.bouquet.BouquetDetailInfoDto;
import kz.bloooom.administration.domain.entity.Bouquet;
import kz.bloooom.administration.service.BouquetBranchPriceService;
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
    BouquetBranchPriceService bouquetBranchPriceService;
    BranchBouquetInfoDtoConverter branchBouquetInfoDtoConverter;

    public BouquetDetailInfoDto convert(Bouquet source) {
        BouquetDetailInfoDto target = new BouquetDetailInfoDto();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setAuthor(source.getEmployee().getName());
        target.setBouquetPhotos(imageInfoConverter.convert(source.getBouquetPhotos()));
        target.setBranchBouquetInfo(branchBouquetInfoDtoConverter
                .convert(bouquetBranchPriceService
                        .getAllBouquetBranchByBouquetId(source.getId())));
        target.setBouquetStyle(source.getBouquetStyle().getName());
        target.setFlowerVarietyInfo(flowerVarietyNameInfoDtoConverter.convert(source.getFlowers(), source.getId()));
        target.setAdditionalElements(additionalElementsNameInfoDtoConverter.convert(source.getAdditionalElements(), source.getId()));
        return target;
    }
}
