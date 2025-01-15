package kz.bloooom.administration.repository;

import kz.bloooom.administration.domain.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByKeycloakId(String keycloakId);
    Employee findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByEmailAndIsDeletedIsFalse(String email);

    boolean existsByPhoneNumber(String phoneNumber);
}
