package kz.bloooom.administration.domain.dto.flower_variety;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlowerVarietyPriceInfoDto {

    @Schema(description = "Id цветка")
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
