package kz.bloooom.administration.domain.dto.bouquet;

import io.swagger.v3.oas.annotations.media.Schema;
import kz.bloooom.administration.domain.dto.additional_elements.AdditionalElementsShortInfoDto;
import kz.bloooom.administration.domain.dto.flower_variety.FlowerVarietyShortInfoToAttachBouquetDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@ToString
@Schema(description = "Обьект для создания букета")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BouquetCreateDto {
    @NotBlank(message = "Название букета не должно быть пустым")
    @Size(min = 1, max = 256)
    @Schema(description = "Название букета")
    String name;

    @NotNull(message = "Id сотрудника не должно быть пустым")
    @Schema(description = "Id сотрудника")
    Long employeeId;

    @Schema(description = "Цена букета")
    Double price;

    @Schema(description = "Id филиала")
    Long branchId;

    @NotNull(message = "Id-ки цветов из которых состоит не должен быть пустым")
    @Schema(description = "Id-ки цветов из которых состоит")
    List<FlowerVarietyShortInfoToAttachBouquetDto> flowersVarietyInfo;

    @NotNull(message = "Id-ки доп элементов не должен быть пустым")
    @Schema(description = "Id-кидоп элементов")
    List<AdditionalElementsShortInfoDto> additionalElements;

    @NotNull(message = "Id стиля букета не должен быть пустым")
    @Schema(description = "Id стиля букета")
    Long bouquetStyleId;
}
