package kz.bloooom.administration.validator.employee;

import kz.bloooom.administration.service.EmployeeService;
import kz.bloooom.administration.validator.EmailChecker;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeEmailChecker implements EmailChecker {

    EmployeeService employeeService;

    @Override
    public boolean emailExists(String email) {
        return employeeService.existsByEmail(email);
    }
}

