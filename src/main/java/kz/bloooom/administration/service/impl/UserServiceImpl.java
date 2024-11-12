package kz.bloooom.administration.service.impl;

import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.domain.entity.User;
import kz.bloooom.administration.exception.BloomAdministrationException;
import kz.bloooom.administration.repository.UserRepository;
import kz.bloooom.administration.service.UserService;
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

    @Override
    public User findByKeycloakId(String keycloakId) {
        return userRepository.findByKeycloakId(keycloakId);
    }
}
