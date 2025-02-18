package kz.bloooom.administration.domain.dto.bouquet;

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
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BouquetDeletePriceDto {
    @NotNull
    @Schema(description = "Id букета")
    Long bouquetId;

    @NotNull
    @Schema(description = "Id филиала")
    Long branchDivisionId;
}
