package kz.bloooom.administration.facade;

import kz.bloooom.administration.domain.dto.employee.EmployeeCreateDto;

public interface EmployeeFacade {
    void create(EmployeeCreateDto dto);
}
