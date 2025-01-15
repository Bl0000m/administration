package kz.bloooom.administration.domain.dto.flower_variety;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import kz.bloooom.administration.enumeration.Currency;
import kz.bloooom.administration.enumeration.Fragrance;
import kz.bloooom.administration.enumeration.Season;
import kz.bloooom.administration.enumeration.StemType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @NotNull(message = "Id сотрудника не должно быть пустым")
    @Schema(description = "Id сотрудника")
    Long employeeId;

    @Schema(description = "Цена")
    Double price;

    @Schema(description = "Id филиала")
    Long branchDivisionId;

    @Schema(description = "Валюта")
    Currency currency;

    @Schema(description = "Дата начала действия цены")
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate validFrom;

    @Schema(description = "Дата окончания действия цены")
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate validTo;

    @Schema(description = "Минимальное количество дней хранения")
    Long shelfLifeDaysMin;

    @Schema(description = "Максимальное количество дней хранения")
    Long shelfLifeDaysMax;

    @Schema(description = "Аромат")
    Fragrance fragranceCode;

    @Schema(description = "Сезон")
    Season season;

    @Schema(description = "Тип стебля на русском")
    StemType steamType;

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

    @Schema(description = "Id информации об ухаде")
    Long stemCareId;

    @Schema(description = "Id информации о температуре")
    Long temperatureCareId;

    @Schema(description = "Id информации о воде")
    Long waterCareId;

    @Schema(description = "Id страны")
    Long countryId;
}
