package kz.bloooom.administration.domain.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@Schema(description = "Обьект для заполнение заказа")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderFillDto {
    @NotNull(message = "Id заказа не должен быть пустым")
    @Schema(description = "Id заказа")
    Long id;

    @NotNull(message = "Id букета не должен быть пустым")
    @Schema(description = "Id букета")
    Long boughtId;

    @Schema(description = "Адрес доставки")
    String address;
}
