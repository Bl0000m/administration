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
public class OrderStatusDto {
    @NotNull(message = "Id заказа не должен быть пустым")
    @Schema(description = "Id заказа")
    Long orderId;

    @NotNull(message = "Id статуса не должен быть пустым")
    @Schema(description = "Id статуса")
    Long statusId;
}
