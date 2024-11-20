package kz.bloooom.administration.repository;

import kz.bloooom.administration.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByKeycloakId(String keycloakId);

    boolean existsByEmail(String email);

    User getByEmail(String email);

    boolean existsByEmailAndIsDeletedIsFalse(String email);

    boolean existsByEmailAndVerifyTrue(String email);

    boolean existsByPhoneNumber(String phoneNumber);
}
