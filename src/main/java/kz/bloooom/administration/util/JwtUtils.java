package kz.bloooom.administration.util;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

@UtilityClass
public class JwtUtils {

    public String getKeycloakId() {
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
    }

    public static String getUserName() {
        try {
            Jwt credentials = getJwt();
            Object mid = credentials.getClaims().get("preferred_username");
            return (String)mid;
        } catch (Exception var2) {
            return getKeycloakId();
        }
    }
    private static Jwt getJwt() {
        return (Jwt)SecurityContextHolder.getContext().getAuthentication().getCredentials();
    }
}
