package kz.bloooom.administration.contant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class KeycloakServiceUrlConstants {

    public static final String TOKEN_PATH = "/realms/%s/protocol/openid-connect/token";
    public static final String GET_SESSION = "%s/admin/realms/%s/users/%s/sessions";
    public static final String RESPONSE_STATUS = "Keycloak user creation response status: %s | status info: %s";
    public static final String FIRST_NAME = "given_name";
    public static final String LAST_NAME = "family_name";
    public static final String KEYCLOAK_ID = "sub";
    public static final String EMAIL = "email";
}
