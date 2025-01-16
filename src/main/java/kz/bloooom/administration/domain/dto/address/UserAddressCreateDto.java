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
@Schema(description = "Обьект для создания адреса пользователя")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAddressCreateDto extends AddressCreateDto {

    @Schema(description = "Id юзера")
    Long userId;
}
