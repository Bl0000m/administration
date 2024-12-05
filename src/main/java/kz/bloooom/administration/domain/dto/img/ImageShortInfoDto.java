package kz.bloooom.administration.domain.dto.img;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Schema(description = "Краткая информация о картинке")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImageShortInfoDto {
    @Schema(description = "Id картинки")
    Long id;

    @Schema(description = "Url картинки")
    String url;
}
