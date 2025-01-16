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
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderAddressInfoDto extends AddressInfoDto {

    @Schema(description = "Телефон получателя")
    String recipientPhone;

    @Schema(description = "Комментарий к доставке")
    String comment;
}
