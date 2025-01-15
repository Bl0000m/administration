package kz.bloooom.administration.converter.bouquet;

import kz.bloooom.administration.converter.img.ImageInfoConverter;
import kz.bloooom.administration.domain.dto.bouquet.BouquetInfoDto;
import kz.bloooom.administration.domain.dto.bouquet.BouquetInfoWithOutPriceDto;
import kz.bloooom.administration.domain.entity.Bouquet;
import kz.bloooom.administration.domain.entity.BouquetBranchPrice;
import kz.bloooom.administration.service.BouquetBranchPriceService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BouquetInfoDtoConverter {
    ImageInfoConverter imageInfoConverter;
    BouquetBranchPriceService bouquetBranchPriceService;

    public BouquetInfoDto convert(Bouquet source) {
        BouquetInfoDto target = new BouquetInfoDto();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setPrice(getLowPrice(source.getId()));
        target.setBouquetPhotos(imageInfoConverter.convert(source.getBouquetPhotos()));
        return target;
    }

    public BouquetInfoWithOutPriceDto convertWithOutPrice(Bouquet source) {
        BouquetInfoWithOutPriceDto target = new BouquetInfoWithOutPriceDto();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setBouquetPhotos(imageInfoConverter.convert(source.getBouquetPhotos()));
        return target;
    }

    private Double getLowPrice(Long bouquetId) {
        List<BouquetBranchPrice> bouquetBranchPrices =
                Optional.ofNullable(bouquetBranchPriceService.getAllBouquetBranchByBouquetId(bouquetId))
                        .orElse(Collections.emptyList());

        return bouquetBranchPrices.stream()
                .map(BouquetBranchPrice::getPrice)
                .filter(Objects::nonNull)
                .min(Double::compareTo)
                .orElse(null);
    }

    public List<BouquetInfoDto> convert(List<Bouquet> bouquets) {
        return CollectionUtils.isEmpty(bouquets) ?
                Collections.emptyList() :
                bouquets.stream().map(this::convert).collect(Collectors.toList());
    }

}
