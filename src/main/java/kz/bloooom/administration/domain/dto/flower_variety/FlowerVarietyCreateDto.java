package kz.bloooom.administration.domain.dto.flower_variety;

import io.swagger.v3.oas.annotations.media.Schema;
import kz.bloooom.administration.enumeration.Fragrance;
import kz.bloooom.administration.enumeration.Season;
import kz.bloooom.administration.enumeration.StemType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlowerVarietyCreateDto {
    @NotBlank(message = "Название не должно быть пустым")
    @Size(min = 1, max = 128)
    @Schema(description = "Название")
    String name;

    @NotNull(message = "Id не должно быть пустым")
    @Schema(description = "Id цветка")
    Long flowerId;

    @Schema(description = "Минимальное количество дней хранения")
    Long shelfLifeDaysMin;

    @Schema(description = "Максимальное количество дней хранения")
    Long shelfLifeDaysMax;

    @Schema(description = "Аромат")
    Fragrance fragranceCode;

    @Schema(description = "Сезон")
    Season season;

    @Schema(name = "Тип стебля на русском")
    StemType steamType;

    @Schema(name = "Минимальный размер бутона в сантиметрах")
    Double budSizeMin;

    @Schema(name = "Максимальный размер бутона в сантиметрах")
    Double budSizeMax;

    @Schema(name = "Минимальная высота стебля в сантиметрах")
    Double stemHeightSizeMin;

    @Schema(name = "Максимальная высота стебля в сантиметрах")
    Double stemHeightSizeMax;

    @Schema(name = "Id информации об ухаде")
    Long stemCareId;

    @Schema(name = "Id информации о температуре")
    Long temperatureCareId;

    @Schema(name = "Id информации о воде")
    Long waterCareId;

    @Schema(name = "Id страны")
    Long countryId;
}
