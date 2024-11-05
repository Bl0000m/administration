package kz.bloooom.administration.domain.dto.company;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@ToString
@Schema(description = "Обьект для отображения компании")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanyInfoDto {

    @Schema(description = "Id компании")
    Long id;

    @Schema(description = "Название компании")
    String name;

    @Schema(description = "БИН компании")
    String bin;

    @Schema(description = "Тип компании")
    String type;

    @Schema(description = "Адрес компании")
    String address;

    @Schema(description = "Контракт id")
    String contractID;

    @Schema(description = "Массив адресов филиалов/представительств")
    List<String> additionalAddress;

    @Schema(description = "Контактный телефон")
    String phone;

    @Schema(description = "Электронная почта")
    String email;

    @Size(min = 1, max = 128)
    @Schema(description = "Сайт компании")
    String website;

    @Schema(description = "Социальные сети компании")
    String socialMedia;
}
