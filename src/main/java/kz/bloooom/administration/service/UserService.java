package kz.bloooom.administration.service;

import kz.bloooom.administration.domain.entity.User;

public interface UserService {
    User getById(Long userId);

    User save(User user);

    boolean existsByEmail(String email);

    boolean existsByEmailAndNotDelete(String email);


    boolean existsByPhoneNumber(String phoneNumber);

    User findByKeycloakId(String keycloakId);
}
