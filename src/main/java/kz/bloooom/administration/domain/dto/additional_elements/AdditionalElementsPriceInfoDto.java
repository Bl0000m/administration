package kz.bloooom.administration.domain.dto.additional_elements;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@Schema(description = "Обьект для показа полной информации о доп-х элементах")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdditionalElementsPriceInfoDto {

    @Schema(description = "Id элемента")
    Long id;

    @Schema(description = "Цена")
    Double price;

    @Schema(description = "Валюта")
    String currency;

    @Schema(description = "Дата начала действия цены")
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDateTime validFrom;

    @Schema(description = "Дата окончания действия цены")
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDateTime validTo;

}
