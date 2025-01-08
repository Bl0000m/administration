package kz.bloooom.administration.converter.bouquet;

import kz.bloooom.administration.domain.dto.bouquet.BouquetCreateDto;
import kz.bloooom.administration.domain.entity.Bouquet;
import kz.bloooom.administration.service.BouquetStyleService;
import kz.bloooom.administration.service.EmployeeService;
import kz.bloooom.administration.util.JwtUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BouquetCreateDtoConverter {
    BouquetStyleService bouquetStyleService;
    EmployeeService employeeService;

    public Bouquet convert(BouquetCreateDto source) {
        Bouquet target = new Bouquet();
        target.setName(source.getName());
        target.setEmployee(employeeService.findByKeycloakId(JwtUtils.getKeycloakId()));
        target.setBouquetStyle(bouquetStyleService.getById(source.getBouquetStyleId()));
        target.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        target.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
        return target;
    }
}
