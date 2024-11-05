package kz.bloooom.administration.domain.dto.company;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "Объект для поиска компании")
public class CompanySearchDto {

    @Schema(description = "Поле для поиска по названию, бин, электранной почте")
    String query;
}
