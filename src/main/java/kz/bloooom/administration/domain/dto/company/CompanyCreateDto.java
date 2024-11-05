package kz.bloooom.administration.domain.dto.company;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@ToString
@Schema(description = "Обьект для создания компании")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanyCreateDto {
    @NotBlank(message = "Название компании не должно быть пустым")
    @Size(min = 1, max = 128)
    @Schema(description = "Название компании")
    String name;

    @NotBlank(message = "БИН компании не должно быть пустым")
    @Size(min = 1, max = 12)
    @Schema(description = "БИН компании")
    String bin;

    @NotNull(message = "Тип компании не должен быть пустым")
    @Schema(description = "Тип компании")
    Long typeId;

    @NotBlank(message = "Адрес компании не должно быть пустым")
    @Size(min = 1, max = 128)
    @Schema(description = "Адрес компании")
    String address;

    @Schema(description = "Массив адресов филиалов/представительств")
    List<String> additionalAddress;

    @NotBlank(message = "Контактный телефон не должно быть пустым")
    @Size(min = 11, max = 11)
    @Schema(description = "Контактный телефон")
    String phone;

    @NotBlank(message = "Электронная почта не должно быть пустым")
    @Size(min = 1, max = 128)
    @Email(message = "email должен соотвествовать формату")
    @Schema(description = "Электронная почта")
    String email;

    @Size(min = 1, max = 128)
    @Schema(description = "Сайт компании")
    String website;

    @Schema(description = "Социальные сети компании")
    String socialMedia;
}
