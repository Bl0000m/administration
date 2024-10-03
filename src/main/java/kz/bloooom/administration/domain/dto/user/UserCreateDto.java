package kz.bloooom.administration.domain.dto.user;

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
@Schema(description = "Обьект для создания юзера через админку (надо предоставлять роль)")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateDto {
    @NotBlank(message = "Поле фамилия пользователя не должно быть пустым")
    @Schema(description = "Фамилия пользователя")
    String name;

    @NotBlank(message = "Поле почта пользователя не должно быть пустым")
    @Email(message = "email должен соотвествовать формату")
    @Schema(description = "Почта пользователя")
    String email;

    @NotBlank(message = "Поле номер телефона пользователя не должно быть пустым")
    @Schema(description = "Номер телефона пользователя")
    String phoneNumber;

    @NotNull(message = "Роль не должен быть пустым")
    @Schema(description = "Роль пользователя")
    Long roleId;
}
