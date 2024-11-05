package kz.bloooom.administration.validator.employee;

import kz.bloooom.administration.service.EmployeeService;
import kz.bloooom.administration.validator.PhoneNumberChecker;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeePhoneNumberChecker implements PhoneNumberChecker {

    EmployeeService employeeService;

    @Override
    public boolean existsPhoneNumber(String phoneNumber) {
        return employeeService.existsByPhoneNumber(phoneNumber);
    }
}

