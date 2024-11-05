package kz.bloooom.administration.service;

import kz.bloooom.administration.domain.entity.Employee;

public interface EmployeeService {
    Employee getById(Long userId);

    Employee save(Employee user);

    boolean existsByEmail(String email);

    boolean existsByEmailAndNotDelete(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    Employee findByKeycloakId(String keycloakId);
}
