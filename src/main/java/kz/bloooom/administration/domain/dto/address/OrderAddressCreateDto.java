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
@Schema(description = "Обьект для создания адреса заказа")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderAddressCreateDto extends AddressCreateDto {

    @Schema(description = "Id юзера")
    Long orderId;

    @Schema(description = "Телефон получателя")
    String recipientPhone;

    @Schema(description = "Комментарий к доставке")
    String comment;
}
