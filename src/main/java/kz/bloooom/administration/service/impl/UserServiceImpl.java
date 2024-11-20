package kz.bloooom.administration.service.impl;

import kz.bloooom.administration.config.KeycloakComponent;
import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.domain.entity.User;
import kz.bloooom.administration.enumeration.role.RoleCode;
import kz.bloooom.administration.exception.BloomAdministrationException;
import kz.bloooom.administration.repository.UserRepository;
import kz.bloooom.administration.service.KeycloakService;
import kz.bloooom.administration.service.UserService;
import kz.bloooom.administration.util.JwtUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    KeycloakComponent keycloakComponent;
    KeycloakService keycloakService;

    @Override
    public User getById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new BloomAdministrationException(
                        HttpStatus.NOT_FOUND,
                        ErrorCodeConstant.USER_WITH_THIS_ID_DOEST_EXISTS,
                        "messages.exception.user-not-found", userId));
    }


    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    @Override
    public User getCurrentUser() {
        String keycloakId = JwtUtils.getKeycloakId();
        return getByKeycloakId(keycloakId);
    }

    @Override
    public User getByKeycloakId(String keycloakId){
        if (keycloakService.getRolesByKeycloakId(keycloakId).stream().anyMatch(s -> s.equals(RoleCode.SUPER_ADMIN.name()))) {
            return keycloakComponent.getUserFromKeycloak();
        } else {
            return userRepository.findByKeycloakId(keycloakId).orElseThrow(() ->
                    new BloomAdministrationException(
                            HttpStatus.NOT_FOUND,
                            ErrorCodeConstant.USER_WITH_THIS_KEYCLOAK_NOT_FOUND,
                            "messages.exception.user-with-keycloak-not-found", keycloakId
                    ));
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByEmailAndNotDelete(String email) {
        return userRepository.existsByEmailAndIsDeletedIsFalse(email);
    }

    @Override
    public boolean isVerifyEmail(String email) {
        return userRepository.existsByEmailAndVerifyTrue(email);
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        return userRepository.existsByPhoneNumber(phoneNumber);
    }
}
