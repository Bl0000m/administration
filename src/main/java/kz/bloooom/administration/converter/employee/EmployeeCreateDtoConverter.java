package kz.bloooom.administration.converter.employee;

import kz.bloooom.administration.domain.dto.employee.EmployeeCreateDto;
import kz.bloooom.administration.domain.entity.Employee;
import kz.bloooom.administration.service.CompanyService;
import kz.bloooom.administration.service.RoleService;
import kz.bloooom.administration.service.StatusService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeCreateDtoConverter {
    CompanyService companyService;
    StatusService statusService;
    RoleService roleService;

    public Employee convert(EmployeeCreateDto source) {
        Employee target = new Employee();
        target.setName(source.getName());
        target.setIin(source.getIin());
        target.setEmail(source.getEmail());
        target.setPosition(source.getPosition());
        target.setPhoneNumber(source.getPhoneNumber());
        target.setStatus(statusService.getById(source.getStatusId()));
        target.setCompany(companyService.getById(source.getCompanyId()));
        target.setRole(roleService.getById(source.getRoleId()));
        target.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        target.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
        target.setDeleted(false);
        return target;
    }
}
