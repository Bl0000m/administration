package kz.bloooom.administration.domain.dto.bouquet_style;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@Builder
@Schema(description = "Обьект для отображения стиля букета")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BouquetStyleInfoDto {
    @Schema(description = "Id")
    Long id;

    @Schema(description = "Наименование")
    String name;

    @Schema(description = "Описание")
    String description;

//    @Schema(description = "Кто создал (keycloakId)")
//    String createdBy;

    @Schema(description = "Флаг, прошел ли проверку")
    Boolean isVerify;
}
