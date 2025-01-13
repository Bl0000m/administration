package kz.bloooom.administration.domain.dto.bouquet;

import io.swagger.v3.oas.annotations.media.Schema;
import kz.bloooom.administration.domain.dto.img.ImageShortInfoDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@ToString
@Schema(description = "Обьект для показа полной информации о букете")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BouquetInfoWithOutPriceDto {
    @Schema(description = "Id букета")
    Long id;

    @Schema(description = "Наименование букета")
    String name;

    @Schema(description = "Фотографии букета")
    Set<ImageShortInfoDto> bouquetPhotos;
}
