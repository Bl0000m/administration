package kz.bloooom.administration.domain.dto.bouquet;

import io.swagger.v3.oas.annotations.media.Schema;
import kz.bloooom.administration.domain.dto.additional_elements.AdditionalElementsInfoDto;
import kz.bloooom.administration.domain.dto.additional_elements.AdditionalElementsNameInfoDto;
import kz.bloooom.administration.domain.dto.flower_variety.FlowerVarietyInfoDto;
import kz.bloooom.administration.domain.dto.flower_variety.FlowerVarietyNameInfoDto;
import kz.bloooom.administration.domain.dto.img.ImageShortInfoDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@Schema(description = "Обьект для показа полной информации о букете")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BouquetDetailInfoDto {
    @Schema(description = "Id букета")
    Long id;

    @Schema(description = "Наименование букета")
    String name;

    @Schema(description = "Наименование компании")
    String companyName;

    @Schema(description = "Фотографии букета")
    Set<ImageShortInfoDto> bouquetPhotos;

    @Schema(description = "Цена букета")
    Double price;

    @Schema(description = "Стиль букета")
    String bouquetStyle;

    @Schema(description = "Информация о цветах в букете")
    List<FlowerVarietyNameInfoDto> flowerVarietyInfo;

    @Schema(description = "Информация о дом элементах букета")
    List<AdditionalElementsNameInfoDto> additionalElements;
}
