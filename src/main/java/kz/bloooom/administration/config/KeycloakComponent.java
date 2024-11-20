package kz.bloooom.administration.config;

import kz.bloooom.administration.domain.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static kz.bloooom.administration.contant.KeycloakServiceUrlConstants.*;

@Component("keycloak")
public class KeycloakComponent {

    public Boolean hasAnyRole(String... keycloakRoles) {
        return getRoles().stream().anyMatch(Arrays.asList(keycloakRoles)::contains);
    }

    public Boolean hasRole(String role) {
        return getRoles().contains(role);
    }

    public List<String> getRoles() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Map<String, List<String>> roles = jwt.getClaim("realm_access");
        return roles.get("roles");
    }

    public User getUserFromKeycloak() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = new User();
        user.setId(0L);
        user.setName(jwt.getClaim(FIRST_NAME));
        user.setKeycloakId(jwt.getClaim(KEYCLOAK_ID));
        user.setEmail(jwt.getClaim(EMAIL));
        return user;
    }
}
