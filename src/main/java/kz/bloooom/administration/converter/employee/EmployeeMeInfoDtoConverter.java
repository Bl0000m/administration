package kz.bloooom.administration.converter.employee;

import kz.bloooom.administration.converter.bouquet.BouquetDetailInfoDtoConverter;
import kz.bloooom.administration.domain.dto.employee.EmployeeMeInfoDto;
import kz.bloooom.administration.domain.entity.Employee;
import kz.bloooom.administration.service.BouquetService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeMeInfoDtoConverter {

    BouquetService bouquetService;
    BouquetDetailInfoDtoConverter bouquetDetailInfoDtoConverter;

    public EmployeeMeInfoDto convert(Employee source) {
        EmployeeMeInfoDto target = new EmployeeMeInfoDto();

        target.setId(source.getId());
        target.setName(source.getName());
        target.setEmail(source.getEmail());
        target.setPhoneNumber(source.getPhoneNumber());
        target.setBranchDivisionId(Objects.nonNull(source.getBranchDivision()) ? source.getBranchDivision().getId() : null);
        target.setBranchDivisionType(Objects.nonNull(source.getBranchDivision()) ? source.getBranchDivision().getDivisionType() : null);
        target.setMyBouquets(bouquetDetailInfoDtoConverter.convert(bouquetService.getAllBouquetsByEmployeeId(source.getId())));
        return target;
    }
}
