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
@Schema(description = "Обьект чтоб присоединить филиал к существующему букету")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BouquetAddBranchDto {
    @NotNull
    @Schema(description = "Id букета")
    Long bouquetId;

    @NotNull
    @Schema(description = "Id филиала")
    Long branchDivisionId;

    @NotNull
    @Schema(description = "Цена")
    Double price;
}
