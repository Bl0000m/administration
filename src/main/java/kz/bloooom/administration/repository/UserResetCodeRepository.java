package kz.bloooom.administration.repository;

import kz.bloooom.administration.domain.entity.UserResetCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserResetCodeRepository extends JpaRepository<UserResetCode, Long> {

    Optional<UserResetCode> findByEmailAndResetCode(String email, String resetCode);
}
