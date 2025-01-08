package kz.bloooom.administration.facade;

import kz.bloooom.administration.domain.dto.employee.EmployeeCreateDto;
import kz.bloooom.administration.domain.dto.employee.ResetUserAuthorizationRequestDto;

public interface EmployeeFacade {
    void create(EmployeeCreateDto dto);

    void userResetAuthorizationPassword(ResetUserAuthorizationRequestDto dto);

}
