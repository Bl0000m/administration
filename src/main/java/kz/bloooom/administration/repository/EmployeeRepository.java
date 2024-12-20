package kz.bloooom.administration.repository;

import kz.bloooom.administration.domain.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByKeycloakId(String keycloakId);

    boolean existsByEmail(String email);

    boolean existsByEmailAndIsDeletedIsFalse(String email);

    boolean existsByPhoneNumber(String phoneNumber);
}
