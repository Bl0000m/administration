package kz.bloooom.administration.converter.bouquet;

import kz.bloooom.administration.converter.img.ImageInfoConverter;
import kz.bloooom.administration.domain.dto.bouquet.BouquetInfoDto;
import kz.bloooom.administration.domain.entity.Bouquet;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BouquetInfoDtoConverter {
    ImageInfoConverter imageInfoConverter;

    public BouquetInfoDto convert(Bouquet source) {
        BouquetInfoDto target = new BouquetInfoDto();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setDiscription(source.getDescription());
        target.setCompanyName(source.getCompany().getName());
        target.setBouquetPhotos(imageInfoConverter.convert(source.getBouquetPhotos()));
        target.setPrice(source.getPrice());
        target.setAddition(source.getAddition());
        return target;
    }
}