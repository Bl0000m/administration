package kz.bloooom.administration.domain.dto.flower_variety;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlowerVarietyUpdateDto {

    @Schema(description = "Цена")
    Double price;

    @Schema(description = "Дата начала действия цены")
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate validFrom;

    @Schema(description = "Дата окончания действия цены")
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate validTo;
}

