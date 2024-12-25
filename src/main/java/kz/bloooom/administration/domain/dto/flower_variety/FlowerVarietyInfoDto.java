package kz.bloooom.administration.domain.dto.flower_variety;

import io.swagger.v3.oas.annotations.media.Schema;
import kz.bloooom.administration.domain.dto.country.CountryInfoDto;
import kz.bloooom.administration.domain.dto.flower.FlowerInfoDto;
import kz.bloooom.administration.domain.dto.stem_care.StemCareInfoDto;
import kz.bloooom.administration.domain.dto.temperature_care.TemperatureCareInfoDto;
import kz.bloooom.administration.domain.dto.water_care.WaterCareInfoDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;

@Getter
@Setter
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlowerVarietyInfoDto {
    @Schema(description = "Id")
    Long id;

    @Schema(description = "Название")
    String name;

    @Schema(description = "Группа цветов")
    FlowerInfoDto flowerInfo;

    @Schema(description = "Минимальное количество дней хранения")
    Long shelfLifeDaysMin;

    @Schema(description = "Максимальное количество дней хранения")
    Long shelfLifeDaysMax;

    @Schema(description = "Аромат")
    FragranceInfoDto fragranceInfo;

    @Schema(description = "Сезон")
    SeasonInfoDto seasonInfo;

    @Schema(description = "Тип стебля на русском")
    StemTypeInfoDto steamTypeInfo;

    @Schema(description = "Цвет")
    String color;

    @Schema(description = "Минимальный размер бутона в сантиметрах")
    Double budSizeMin;

    @Schema(description = "Максимальный размер бутона в сантиметрах")
    Double budSizeMax;

    @Schema(description = "Минимальная высота стебля в сантиметрах")
    Double stemHeightSizeMin;

    @Schema(description = "Максимальная высота стебля в сантиметрах")
    Double stemHeightSizeMax;

    @Schema(description = "Фото")
    String image;

    @Schema(description = "Id информации об ухаде")
    StemCareInfoDto stemCareInfo;

    @Schema(description = "Id информации о температуре")
    TemperatureCareInfoDto temperatureCareInfo;

    @Schema(description = "Id информации о воде")
    WaterCareInfoDto waterCareInfo;

    @Schema(description = "Id страны")
    CountryInfoDto countryInfoDto;
}