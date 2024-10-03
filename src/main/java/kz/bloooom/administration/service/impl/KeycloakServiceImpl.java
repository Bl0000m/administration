package kz.bloooom.administration.service.impl;


import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.contant.KeycloakServiceUrlConstants;
import kz.bloooom.administration.contant.OAuth2Constants;
import kz.bloooom.administration.converter.user.UserToUserRepresentationConverter;
import kz.bloooom.administration.domain.dto.keycloak.KeycloakAuthRequestDto;
import kz.bloooom.administration.domain.dto.role.RoleMappingDto;
import kz.bloooom.administration.domain.dto.role.RoleRepresentationDeleteDto;
import kz.bloooom.administration.domain.dto.role.RoleRepresentationDto;
import kz.bloooom.administration.domain.entity.Role;
import kz.bloooom.administration.domain.entity.User;
import kz.bloooom.administration.enumeration.role.RoleCode;
import kz.bloooom.administration.exception.BloomAdministrationException;
import kz.bloooom.administration.service.KeycloakService;
import kz.bloooom.administration.util.JwtUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.admin.client.token.TokenManager;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.keycloak.representations.idm.UserSessionRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import java.util.*;
import java.util.stream.Collectors;

import static kz.bloooom.administration.contant.KeycloakServiceUrlConstants.RESPONSE_STATUS;


@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KeycloakServiceImpl implements KeycloakService {

    public static final String KEYCLOAK_ROLE_MAPPING_REALM = "%s/admin/realms/%s/users/%s/role-mappings/realm";
    public static final String KEYCLOAK_ROLE_MAPPING = "%s/admin/realms/%s/users/%s/role-mappings";
    public static final String KEYCLOAK_ROLE_MAPPING_AVAILABLE = "%s/admin/realms/%s/users/%s/role-mappings/realm/available";
    UsersResource usersResource;
    RestTemplate restTemplate;
    UserToUserRepresentationConverter userToUserRepresentationConverter;

    @NonFinal
    @Value("${keycloak.auth-server-url}")
    String url;

    @NonFinal
    @Value("${keycloak.realm}")
    String myRealm;

    @NonFinal
    @Value("${keycloak.resource}")
    String clientName;

    @NonFinal
    @Value("${keycloak.client-secret}")
    String clientSecret;

    @NonFinal
    @Value("${keycloak.username}")
    private String realmUsername;

    @NonFinal
    @Value("${keycloak.password}")
    private String realmPassword;
//
//    @NonFinal
//    @Value("${keycloak-service-account.username}")
//    private String serviceTokenUsername;

    @NonFinal
    Keycloak userKeycloak = null;

    /**
     * {@inheritDoc}
     */
    @Override
    public AccessTokenResponse login(KeycloakAuthRequestDto dto) {
        try {
            log.info("KeycloakServiceImpl | Username: " + dto.getUsername());
            String accessTokenUrl = StringUtils.join(
                    url,
                    String.format(KeycloakServiceUrlConstants.TOKEN_PATH, myRealm)
            );
            HttpEntity<MultiValueMap<String, String>> entity =
                    new HttpEntity<>(
                            getAuthTokenRequestBody(StringUtils.trim(dto.getUsername()), StringUtils.trim(dto.getPassword())),
                            getRefreshTokenRequestHeaders()
                    );
            ResponseEntity<AccessTokenResponse> accessTokenResponse =
                    restTemplate.exchange(accessTokenUrl, HttpMethod.POST, entity, AccessTokenResponse.class);
            return accessTokenResponse.getBody();

        } catch (Exception e) {
            UUID uuid = UUID.randomUUID();
            log.error("KeycloakServiceImpl:login:Exception: uuid={}: username={}", uuid, dto.getUsername(), e);
            throw new BloomAdministrationException(HttpStatus.INTERNAL_SERVER_ERROR,
                    ErrorCodeConstant.WRONG_LOGIN_OR_PASSWORD,
                    "messages.exception.login-error", uuid.toString()
            );
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param user     Запрос создания пользователя
     * @param password Пароль пользователя
     * @return {@link String}
     */
    @Override
    public String createUserAndGetKeycloakId(User user, String password) {
        UserRepresentation userRepresentation = userToUserRepresentationConverter.convert(user);
        userRepresentation.setEmailVerified(true);
        setUserRepresentationCredentials(userRepresentation, password);
        userKeycloak = Keycloak.getInstance(url, myRealm, realmUsername, realmPassword, clientName, clientSecret);
        UsersResource usersResource = userKeycloak.realm(myRealm).users();
        try (Response response = usersResource.create(userRepresentation)) {
            log.info(String.format(RESPONSE_STATUS, response.getStatus(), response.getStatusInfo()));
            if (response.getStatus() > 201) {
                throw new BloomAdministrationException(
                        HttpStatus.BAD_REQUEST,
                        ErrorCodeConstant.CREATE_KEYCLOAK_USER_ERROR,
                        "messages.exception.create-keycloak-user-error"
                );
            }
        }
        return getKeycloakId(userRepresentation.getUsername());
    }

    @Override
    public List<UserSessionRepresentation> getUserSession(String keycloakId) {
        String urlRest = String.format(KeycloakServiceUrlConstants.GET_SESSION, url, myRealm, keycloakId);
        HttpHeaders headers = getHttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List<UserSessionRepresentation>> responseEntity = restTemplate.exchange(urlRest, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {
        });
        return responseEntity.getBody();
    }

    @Override
    public void clearSessionUser(List<UserSessionRepresentation> userSessionRepresentations) {
        for (UserSessionRepresentation representation : userSessionRepresentations) {
            String urlRest = String.format("%s/admin/realms/%s/sessions/%s", url, myRealm, representation.getId());
            HttpHeaders headers = getHttpHeaders();
            HttpEntity<String> entity = new HttpEntity<>(headers);
            restTemplate.exchange(urlRest, HttpMethod.DELETE, entity, new ParameterizedTypeReference<>() {
            });
        }
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        userKeycloak = Keycloak.getInstance(url, myRealm, realmUsername, realmPassword, clientName, clientSecret);
        KeycloakAuthRequestDto dto = new KeycloakAuthRequestDto();
        dto.setUsername(realmUsername);
        dto.setPassword(realmPassword);
        headers.set("Authorization", "Bearer " + login(dto).getToken());
        return headers;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AccessTokenResponse refresh(String refreshToken) {
        try {
            String accessTokenUrl = StringUtils.join(
                    url,
                    String.format(KeycloakServiceUrlConstants.TOKEN_PATH, myRealm)
            );
            HttpEntity<MultiValueMap<String, String>> entity =
                    new HttpEntity<>(
                            getRefreshTokenRequestBody(refreshToken),
                            getRefreshTokenRequestHeaders()
                    );
            ResponseEntity<AccessTokenResponse> accessTokenResponse =
                    restTemplate.exchange(accessTokenUrl, HttpMethod.POST, entity, AccessTokenResponse.class);
            return accessTokenResponse.getBody();
        } catch (Exception e) {
            log.error("KeycloakServiceImpl:refresh:Exception: refreshToken={}", refreshToken, e);
            throw new BloomAdministrationException(
                    HttpStatus.BAD_REQUEST,
                    ErrorCodeConstant.REFRESH_TOKEN_INCORRECT,
                    "messages.exception.refresh-token-incorrect", refreshToken);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetPassword(String newPassword) {
        usersResource.get(JwtUtils.getKeycloakId()).resetPassword(getPasswordCredential(newPassword));
    }

    @Override
    public void resetPassword(String token, String newPassword) {
        usersResource.get(token).resetPassword(getPasswordCredential(newPassword));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void forgotPassword(String keycloakId, String newPassword) {
        userKeycloak = Keycloak.getInstance(url, myRealm, realmUsername, realmPassword, clientName, clientSecret);
        RealmResource realmResource = userKeycloak.realm(myRealm);
        realmResource.users().get(keycloakId).resetPassword(getPasswordCredential(newPassword));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean verifyCurrentUserPassword(String currentPassword) {
        try {
//          Здесь происходит попытка инициализации кейклока.
//          При вводе правильного пароля он сгенерирует новый токен.
//          В ином случае кейклок выдаст 401, после чего этот метод вернет false
            userKeycloak = Keycloak.getInstance(url, myRealm,
                    JwtUtils.getUserName(), currentPassword,
                    clientName, clientSecret
            );
            userKeycloak.tokenManager().grantToken();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateKeycloakUsername(String keycloakId, String newUsername) {
        log.info("KeycloakServiceImpl:updateKeycloakUsername: keycloakId={}, newUsername={}",
                keycloakId, newUsername
        );
        UserResource userResource = usersResource.get(keycloakId);
        UserRepresentation userRepresentation = userResource.toRepresentation();
        userRepresentation.setEmail(newUsername);
        userRepresentation.setUsername(newUsername);
        userResource.update(userRepresentation);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUserDisabled(String keycloakId) {
        log.info("Метод setUserDisabled активировался с параметром keycloakId={}", keycloakId);
        UserRepresentation userRepresentation = usersResource.get(keycloakId).toRepresentation();
        userRepresentation.setEnabled(false);
        usersResource.get(keycloakId).update(userRepresentation);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createRoleByDifferent(List<Role> rolesFromDB) {
        try {
            userKeycloak = Keycloak.getInstance(url, myRealm, realmUsername, realmPassword, clientName, clientSecret);
            RealmResource realm = userKeycloak.realm(myRealm);
            List<String> roleKeycloakList = realm.roles().list().stream()
                    .map(RoleRepresentation::getName).collect(Collectors.toList());
            rolesFromDB.stream()
                    .filter(role -> !roleKeycloakList.contains(role.getCode().name()))
                    .forEach(roleDifferent -> {
                                RoleRepresentation roleRepresentation = new RoleRepresentation();
                                roleRepresentation.setName(roleDifferent.getCode().name());
                                roleRepresentation.setDescription(roleDifferent.getName().get("en"));
                                realm.roles().create(roleRepresentation);
                            }
                    );
        } catch (Exception e) {
            e.getStackTrace();
        }
    }


    @Override
    public void assignRoleToUser(String keycloakId, List<RoleRepresentationDto> representationDto) {
        representationDto.forEach(roleRepresentationDto -> assignRoleToUser(keycloakId, roleRepresentationDto));
    }


    @Override
    public void assignRoleToUser(String keycloakId, RoleRepresentationDto... representationDto) {

        String urlRest = String.format(KEYCLOAK_ROLE_MAPPING_REALM, url, myRealm, keycloakId);
        HttpHeaders headers = getHttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<List<RoleRepresentationDto>> entity = new HttpEntity<>(List.of(representationDto), headers);

        ResponseEntity<List<RoleRepresentationDto>> response = restTemplate
                .exchange(
                        urlRest,
                        HttpMethod.POST,
                        entity,
                        new ParameterizedTypeReference<>() {
                        }
                );

        if (response.getStatusCode().is2xxSuccessful()) {
            log.info("unpin all roles from user keycloakId = {}", keycloakId);
        } else {
            log.warn("error unpin roles from user keycloakId = {}", keycloakId);
        }
    }

    @Override
    public void unpinUserRole(String keycloakId, List<RoleRepresentationDto> representationDto) {
        representationDto.forEach(roleRepresentationDto -> unpinUserRole(keycloakId, roleRepresentationDto));
    }

    @Override
    public void unpinUserRole(String keycloakId, RoleRepresentationDto... representationDto) {
        String urlRest = String.format(KEYCLOAK_ROLE_MAPPING_REALM, url, myRealm, keycloakId);
        HttpHeaders headers = getHttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        List<RoleRepresentationDeleteDto> roleRepresentationDeleteDtoList = new ArrayList<>();

        for (RoleRepresentationDto item : representationDto) {
            roleRepresentationDeleteDtoList.add(new RoleRepresentationDeleteDto(item.getId(), item.getName()));
        }

        HttpEntity<List<RoleRepresentationDeleteDto>> entity = new HttpEntity<>(roleRepresentationDeleteDtoList, headers);

        ResponseEntity<List<RoleRepresentationDto>> response = restTemplate
                .exchange(
                        urlRest,
                        HttpMethod.DELETE,
                        entity,
                        new ParameterizedTypeReference<>() {
                        }
                );

        if (response.getStatusCode().is2xxSuccessful()) {
            log.info("unpin all roles from user keycloakId = {}", keycloakId);
        } else {
            log.warn("error unpin roles from user keycloakId = {}", keycloakId);
        }
    }

    @Override
    @Transactional
    public void rolesUnpinAndAssignToUser(String userKeycloakId, List<RoleCode> roleCodes) {
        // get all roles from user
        List<RoleRepresentationDto> roleRepresentationDtoList = getRoleByKeycloakId(userKeycloakId).getRealmMappings();

        // clear roles from user
        if (Objects.nonNull(roleRepresentationDtoList)) {
            unpinUserRole(userKeycloakId, roleRepresentationDtoList);
        }

        if (Objects.nonNull(roleCodes) && BooleanUtils.isFalse(roleCodes.isEmpty())) {
            List<String> rolesString = roleCodes.stream().map(Enum::name).collect(Collectors.toList());
            List<RoleRepresentationDto> representationDtoList = getAvailableRoles(userKeycloakId)
                    .stream()
                    .filter(roleRepresentationDto ->
                            rolesString.contains(roleRepresentationDto.getName())).collect(Collectors.toList());

            // added roles from user
            assignRoleToUser(userKeycloakId, representationDtoList);
        }
    }

    @Override
    public RoleMappingDto getRoleByKeycloakId(String keycloakId) {
        String urlRest = String.format(KEYCLOAK_ROLE_MAPPING, url, myRealm, keycloakId);
        HttpHeaders headers = getHttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<RoleMappingDto> roleRepresentationDtoList = restTemplate.exchange(urlRest, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {
        });

        if (roleRepresentationDtoList.getStatusCode().is5xxServerError())
            return null;

        return roleRepresentationDtoList.getBody();
    }

    @Override
    public List<RoleRepresentationDto> getAvailableRoles(String keycloakId) {
        String urlRest = String.format(KEYCLOAK_ROLE_MAPPING_AVAILABLE, url, myRealm, keycloakId);

        HttpHeaders headers = getHttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List<RoleRepresentationDto>> roleRepresentationDtoList = restTemplate
                .exchange(
                        urlRest,
                        HttpMethod.GET,
                        entity,
                        new ParameterizedTypeReference<>() {
                        }
                );

        return roleRepresentationDtoList.getBody();
    }

    @Override
    public String getKeycloakId(String username) {
        List<UserRepresentation> userRepresentations = usersResource.search(username);
        if (userRepresentations.isEmpty()) {
            throw new BloomAdministrationException(
                    HttpStatus.BAD_REQUEST,
                    ErrorCodeConstant.USERNAME_INCORRECT,
                    "messages.exception.username-incorrect", username);
        }
        UserRepresentation userRepresentation = userRepresentations.get(0);
        return userRepresentation.getId();
    }

    @Override
    public void deleteUserByKeycloakIdIfExists(String keycloakId) {
        UserResource userResource = usersResource.get(keycloakId);
        try {
            userResource.remove();
            log.info(String.format("Пользователь c keycloakId %s был успешно удален", keycloakId));
        } catch (NotFoundException e) {
            log.error(String.format("Пользователь с keycloakId %s не найден.", keycloakId));
        }
    }

    private CredentialRepresentation getPasswordCredential(String password) {
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(password);
        credentialRepresentation.setTemporary(false);
        return credentialRepresentation;
    }


    private MultiValueMap<String, String> getRefreshTokenRequestBody(String refreshToken) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add(OAuth2Constants.GRANT_TYPE, OAuth2Constants.REFRESH_TOKEN);
        parameters.add(OAuth2Constants.REFRESH_TOKEN, refreshToken);
        parameters.add(OAuth2Constants.CLIENT_ID, clientName);
        parameters.add(OAuth2Constants.CLIENT_SECRET, clientSecret);
        return parameters;
    }

    private MultiValueMap<String, String> getAuthTokenRequestBody(String login, String password) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add(OAuth2Constants.GRANT_TYPE, OAuth2Constants.PASSWORD);
        parameters.add(OAuth2Constants.CLIENT_ID, clientName);
        parameters.add(OAuth2Constants.CLIENT_SECRET, clientSecret);
        parameters.add(OAuth2Constants.USERNAME, login);
        parameters.add(OAuth2Constants.PASSWORD, password);
        return parameters;
    }

    private HttpHeaders getRefreshTokenRequestHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return headers;
    }

    private void setUserRepresentationCredentials(UserRepresentation user, String password) {
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(password);
        user.setCredentials(Collections.singletonList(credentialRepresentation));
    }

    @Override
    public void logout() {
        userKeycloak = Keycloak.getInstance(url, myRealm, realmUsername, realmPassword, clientName, clientSecret);
        RealmResource realm = userKeycloak.realm(myRealm);
        UserResource userResource = realm.users().get(JwtUtils.getKeycloakId());
        userResource.logout();
    }

    @Override
    public List<String> getRolesByKeycloakId(String keycloakId) {
        return usersResource.get(keycloakId).roles().realmLevel().listAll()
                .stream().map(RoleRepresentation::getName).collect(Collectors.toList());
    }

    @Override
    public List<String> getRolesByUsername(String username) {
        List<UserRepresentation> userRepresentations = usersResource.searchByUsername(username, true);
        if (CollectionUtils.isEmpty(userRepresentations)) {
            throw new BloomAdministrationException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    ErrorCodeConstant.USER_WITH_THIS_EMAIL_NOT_FOUND,
                    "messages.exception.user-with-this-email-not-found"
            );
        }
        UserRepresentation userRepresentation = userRepresentations.get(0);
        return getRolesByKeycloakId(userRepresentation.getId());
    }

    @Override
    public String getServiceToken() {
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(url)
                .realm(myRealm)
                .grantType(org.keycloak.OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId(clientName)
                .clientSecret(clientSecret)
                .build();

        TokenManager tokenManager = keycloak.tokenManager();
        AccessTokenResponse tokenResponse = tokenManager.getAccessToken();

        return tokenResponse.getToken();
    }
}
