package kz.bloooom.administration.domain.dto.stem_care;

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
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StemCareCreateDto {
    @NotBlank(message = "Название не должно быть пустым")
    @Size(min = 1, max = 128)
    @Schema(description = "Название")
    String name;

    @NotBlank(message = "Описание не должно быть пустым")
    @Size(min = 1, max = 128)
    @Schema(description = "Описание")
    String description;
}
