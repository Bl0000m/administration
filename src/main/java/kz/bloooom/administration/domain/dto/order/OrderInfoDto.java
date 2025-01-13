package kz.bloooom.administration.domain.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import kz.bloooom.administration.domain.dto.bouquet.BouquetInfoDto;
import kz.bloooom.administration.domain.dto.bouquet.BouquetInfoWithOutPriceDto;
import kz.bloooom.administration.domain.dto.branch_division.BranchDivisionInfoDto;
import kz.bloooom.administration.domain.dto.branch_division.BranchDivisionShortDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@ToString
@Schema(description = "Обьект для показа полной информации о заказе")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderInfoDto {
    @Schema(description = "Id заказа")
    Long id;

    @Schema(description = "Номер заказа")
    Long orderCode;

    @Schema(description = "Адрес доствки")
    String address;

    @Schema(description = "Информаци о букете")
    BouquetInfoWithOutPriceDto bouquetInfo;

    @Schema(description = "Информация о магазине")
    BranchDivisionShortDto branchDivisionInfoDto;

    @Schema(description = "Цена выбранного букета")
    Double assemblyCost;

    @Schema(description = "Дата доставки")
    LocalDate deliveryDate;

    @Schema(description = "Начало время доставки")
    LocalTime deliveryStartTime;

    @Schema(description = "Окончание время доставки")
    LocalTime deliveryEndTime;

    @Schema(description = "Статус заказа")
    String orderStatus;
}

