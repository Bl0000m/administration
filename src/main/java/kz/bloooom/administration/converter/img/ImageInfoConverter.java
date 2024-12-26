package kz.bloooom.administration.converter.img;

import kz.bloooom.administration.domain.dto.img.ImageShortInfoDto;
import kz.bloooom.administration.domain.entity.BouquetPhoto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImageInfoConverter {
    @Value("${cloud.minio.host}")
    String host;

    @Value("${cloud.minio.bucket}")
    String bucket;

    public ImageShortInfoDto convert(BouquetPhoto source) {
        ImageShortInfoDto target = new ImageShortInfoDto();
        target.setId(source.getId());
        target.setUrl(getImagesUrl(source));
        return target;
    }

    public Set<ImageShortInfoDto> convert(Set<BouquetPhoto> bouquetPhotos) {
        return CollectionUtils.isEmpty(bouquetPhotos) ?
                Collections.emptySet() :
                bouquetPhotos.stream().map(this::convert).collect(Collectors.toSet());
    }

    private String getImagesUrl(BouquetPhoto source) {
        return host + "/" + bucket + "/" + source.getTitle();
    }
}
