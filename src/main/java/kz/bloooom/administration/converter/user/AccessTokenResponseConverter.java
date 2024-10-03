package kz.bloooom.administration.converter.user;

import kz.bloooom.administration.domain.dto.keycloak.KeycloakAuthResponseDto;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

@Component
public class AccessTokenResponseConverter {

    public KeycloakAuthResponseDto convert(AccessTokenResponse source) {
        KeycloakAuthResponseDto target = new KeycloakAuthResponseDto();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, (int) source.getRefreshExpiresIn());
        String refreshTokenExpDate = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSSX",
                Locale.getDefault(Locale.Category.FORMAT)
        ).format(calendar.getTime());
        target.setAccessToken(source.getToken());
        target.setExpiresIn((int) source.getExpiresIn());
        target.setRefreshExpiresIn((int) source.getRefreshExpiresIn());
        target.setRefreshToken(source.getRefreshToken());
        target.setRefreshTokenExpDate(refreshTokenExpDate);
        target.setTokenType(source.getTokenType());
        target.setSessionState(source.getSessionState());
        target.setScope(source.getScope());
        return target;
    }
}
