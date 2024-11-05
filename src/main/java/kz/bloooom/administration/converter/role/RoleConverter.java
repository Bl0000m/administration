package kz.bloooom.administration.converter.role;


import kz.bloooom.administration.converter.AbstractEnumDtoConverter;
import kz.bloooom.administration.domain.entity.Role;
import kz.bloooom.administration.enumeration.role.RoleCode;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter
        extends AbstractEnumDtoConverter<RoleCode, Role> {
}
