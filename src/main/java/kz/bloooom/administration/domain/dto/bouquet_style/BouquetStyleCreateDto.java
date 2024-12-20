package kz.bloooom.administration.domain.dto.bouquet_style;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@Schema(description = "Обьект для создания стиля букета")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BouquetStyleCreateDto {
    @NotBlank(message = "Название стиля букета не должно быть пустым")
    @Size(min = 1, max = 128)
    @Schema(description = "Название стиля букета")
    String name;

    @NotBlank(message = "Описание стиля букета не должно быть пустым")
    @Size(min = 1, max = 128)
    @Schema(description = "Описание компании")
    String description;
}
