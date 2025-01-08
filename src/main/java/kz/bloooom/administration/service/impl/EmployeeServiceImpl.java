package kz.bloooom.administration.service.impl;

import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.domain.entity.Employee;
import kz.bloooom.administration.exception.BloomAdministrationException;
import kz.bloooom.administration.repository.EmployeeRepository;
import kz.bloooom.administration.service.EmployeeService;
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
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeRepository employeeRepository;

    @Override
    public Employee getById(Long userId) {
        return employeeRepository.findById(userId)
                .orElseThrow(() -> new BloomAdministrationException(
                        HttpStatus.NOT_FOUND,
                        ErrorCodeConstant.USER_WITH_THIS_ID_DOEST_EXISTS,
                        "messages.exception.user-not-found", userId));
    }

    @Override
    @Transactional
    public Employee save(Employee user) {
        return employeeRepository.save(user);
    }

    @Override
    public boolean existsByEmail(String email) {
        return employeeRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByEmailAndNotDelete(String email) {
        return employeeRepository.existsByEmailAndIsDeletedIsFalse(email);
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        return employeeRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public Employee findByKeycloakId(String keycloakId) {
        return employeeRepository.findByKeycloakId(keycloakId);
    }

    @Override
    public Employee findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }
}
