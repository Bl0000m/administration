package kz.bloooom.administration.config;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KeycloakConfig {

    @Value("${keycloak.auth-server-url}")
    String url;

    @Value("${keycloak.realm}")
    String myRealm;

    @Value("${keycloak.resource}")
    String clientName;

    @Value("${keycloak.client-secret}")
    String clientSecret;

    @Value("${keycloak.username}")
    String username;

    @Value("${keycloak.password}")
    String password;

    @Bean
    public UsersResource usersResource() {
        return Keycloak.getInstance(url, myRealm, username, password, clientName, clientSecret)
                .realm(myRealm).users();
    }

}