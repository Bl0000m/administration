package kz.bloooom.administration.domain.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@Schema(description = "Обьект для создания сотрудника")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeCreateDto {
    @NotBlank(message = "Поле ФИО пользователя не должно быть пустым")
    @Schema(description = "ФИО пользователя")
    String name;

    @NotBlank(message = "Поле ИИН пользователя не должно быть пустым")
    @Schema(description = "ИИН пользователя")
    String iin;

    @NotNull(message = "Id компании не должен быть пустым")
    @Schema(description = "Id компании")
    Long companyId;

    @NotNull(message = "Id роли не должен быть пустым")
    @Schema(description = "Id роли")
    Long roleId;

    @NotBlank(message = "Поле ИИН пользователя не должно быть пустым")
    @Schema(description = "ИИН пользователя")
    String position;

    @NotBlank(message = "Поле почта пользователя не должно быть пустым")
    @Email(message = "email должен соотвествовать формату")
    @Schema(description = "Почта пользователя")
    String email;

    @NotBlank(message = "Поле номер телефона пользователя не должно быть пустым")
    @Schema(description = "Номер телефона пользователя")
    String phoneNumber;

    @NotNull(message = "Статус не должен быть пустым")
    @Schema(description = "Id статуса")
    Long statusId;
}
