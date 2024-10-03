package kz.bloooom.administration.domain.dto.keycloak;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Объект передачи данных для авторизации пользователя")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KeycloakAuthRequestDto {
    @NotBlank(message = "Имя пользователя не должно быть пустым")
    @Schema(description = "Имя пользователя для keycloak")
    String username;

    @NotBlank(message = "Пароль не должен быть пустым")
    @Schema(description = "Пароль для keycloak пользователя")
    String password;

    @JsonIgnore
    String grant_type;

    @JsonIgnore
    String client_id;

    @JsonIgnore
    String client_secret;

    public KeycloakAuthRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}