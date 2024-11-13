package kz.bloooom.administration.domain.dto.keycloak;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Schema(description = "Объект передачи данных для получения refresh token'а")
public class KeycloakAuthWithRefreshTokenDto {

    @NotBlank
    @Schema(description = "refresh token для обновления keycloak access token'а")
    private String refreshToken;
}
