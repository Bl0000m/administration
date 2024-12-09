package kz.bloooom.administration.domain.dto.subscription;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Schema(description = "Обьект времяни")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderTimeDto {
    @Schema(description = "Дата заказов", example = "2024-12-09")
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate orderDate;

    @Schema(description = "Время начало заказа", example = "08:00")
    @JsonFormat(pattern = "HH:mm")
    LocalTime orderStartTime;

    @Schema(description = "Время окончание заказа", example = "12:59")
    @JsonFormat(pattern = "HH:mm")
    LocalTime orderEndTime;
}
