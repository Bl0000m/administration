package kz.bloooom.administration.domain.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import kz.bloooom.administration.domain.dto.bouquet.BouquetDetailInfoDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeMeInfoDto {

    @Schema(description = "Id пользователя")
    Long id;

    @Schema(description = "ФИО человека")
    String name;

    @Schema(description = "Телефон пользователя")
    String phoneNumber;

    @Schema(description = "Id подразделения ")
    Long branchDivisionId;

    @Schema(description = "Тип подразделения ")
    String branchDivisionType;

    @Schema(description = "Почта пользователя")
    String email;

    @Schema(description = "Мои букеты")
    List<BouquetDetailInfoDto> myBouquets;
}
