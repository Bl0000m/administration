package kz.bloooom.administration.service;

import kz.bloooom.administration.domain.entity.User;

public interface UserService {
    User getById(Long userId);

    User save(User user);

    User getByEmail(String email);

    User getCurrentUser();

    User getByKeycloakId(String keycloakId);

    boolean existsByEmail(String email);

    boolean existsByEmailAndNotDelete(String email);

    boolean isVerifyEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

}
