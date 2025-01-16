package kz.bloooom.administration.domain.dto.address;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@Schema(description = "Обьект для создания адреса")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressCreateDto {
    @Schema(description = "Название улицы")
    String street;

    @Schema(description = "Номер здания")
    String building;

    @Schema(description = "Номер квартиры")
    String apartment;

    @Schema(description = "Номер подъезда")
    String entrance;

    @Schema(description = "Номер домофона")
    String intercom;

    @Schema(description = "Этаж")
    Integer floor;

    @Schema(description = "Город")
    String city;

    @Schema(description = "Почтовый индекс")
    String postalCode;

    @Schema(description = "Географическая широта")
    Double latitude;

    @Schema(description = "Географическая долгота")
    Double longitude;
}
