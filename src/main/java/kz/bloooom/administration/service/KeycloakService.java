package kz.bloooom.administration.service;


import kz.bloooom.administration.domain.dto.keycloak.KeycloakAuthRequestDto;
import kz.bloooom.administration.domain.dto.role.RoleMappingDto;
import kz.bloooom.administration.domain.dto.role.RoleRepresentationDto;
import kz.bloooom.administration.domain.entity.Role;
import kz.bloooom.administration.domain.entity.User;
import kz.bloooom.administration.enumeration.role.RoleCode;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.UserSessionRepresentation;

import java.util.List;

public interface KeycloakService {

    /**
     * Создание пользователя и получение его keycloakId
     *
     * @param user     Запрос создания пользователя
     * @param password Пароль пользователя
     * @return id Keycloak
     */
    String createUserAndGetKeycloakId(User user, String password);

    List<UserSessionRepresentation> getUserSession(String keycloakId);

    void clearSessionUser(List<UserSessionRepresentation> userSessionRepresentations);

    /**
     * Авторизация пользователя
     *
     * @param keycloakAuthRequestDto Запрос авторизации
     * @return Токен доступа
     */
    AccessTokenResponse login(KeycloakAuthRequestDto keycloakAuthRequestDto);

    /**
     * Обновление токена доступа пользователя
     *
     * @param refreshToken Токен обновления
     * @return Новый токен доступа
     */
    AccessTokenResponse refresh(String refreshToken);

    String getKeycloakId(String username);

    void deleteUserByKeycloakIdIfExists(String keycloakId);

    /**
     * Сброс пароля пользователя
     *
     * @param newPassword Новый парль пользователя
     */
    void resetPassword(String newPassword);

    void resetPassword(String token, String newPassword);


    /**
     * Функция забыл пароль
     *
     * @param newPassword Новый парль пользователя
     * @param email       Почта пользователя
     */
    void forgotPassword(String email, String newPassword);

    /**
     * Проверка текущего пароля пользователя
     *
     * @param currentPassword Проверяемый пароль
     * @return Совпадают ли пароли
     */
    boolean verifyCurrentUserPassword(String currentPassword);

    /**
     * Изменение юзернейма пользователя в кейклоке
     *
     * @param keycloakId  keycloakId пользователя
     * @param newUsername Новый юзернейм пользователя
     */
    void updateKeycloakUsername(String keycloakId, String newUsername);

    /**
     * Изменение статуса пользователя в кейклоке: User Enabled -> on/off
     *
     * @param keycloakId keycloakId пользователя
     */
    void setUserDisabled(String keycloakId);


    /**
     * Создания новой роли
     *
     * @param rolesFromDB новая роль для keycloak
     */
    void createRoleByDifferent(List<Role> rolesFromDB);

    /**
     * Метод для назначение роли пользователю
     *
     * @param keycloakId        имя пользователя
     * @param representationDto список ролей
     */
    void assignRoleToUser(String keycloakId, RoleRepresentationDto... representationDto);

    /**
     * Метод для назначение роли пользователю
     *
     * @param keycloakId        имя пользователя
     * @param representationDto список ролей
     */
    void assignRoleToUser(String keycloakId, List<RoleRepresentationDto> representationDto);


    void rolesUnpinAndAssignToUser(String userKeycloakId, List<RoleCode> roleCodes);


    RoleMappingDto getRoleByKeycloakId(String keycloakId);


    List<RoleRepresentationDto> getAvailableRoles(String keycloakId);


    void unpinUserRole(String keycloakId, RoleRepresentationDto... representationDto);


    void unpinUserRole(String keycloakId, List<RoleRepresentationDto> representationDto);

    void logout();

    String getServiceToken();

    List<String> getRolesByKeycloakId(String keycloakId);

    List<String> getRolesByUsername(String username);
}
