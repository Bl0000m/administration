package kz.bloooom.administration.repository;

import kz.bloooom.administration.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByKeycloakId(String keycloakId);

    boolean existsByEmail(String email);

    boolean existsByEmailAndIsDeletedIsFalse(String email);


    boolean existsByPhoneNumber(String phoneNumber);
}
