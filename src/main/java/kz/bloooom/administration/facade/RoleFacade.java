package kz.bloooom.administration.facade;


import kz.bloooom.administration.domain.dto.AbstractEnumDto;
import kz.bloooom.administration.domain.dto.role.RoleDto;
import kz.bloooom.administration.enumeration.role.RoleCode;

import java.util.List;

public interface RoleFacade {
    List<AbstractEnumDto<RoleCode>> getAllRoles();

    AbstractEnumDto<RoleCode> getRoleById(Long id);

    AbstractEnumDto<RoleCode> getRoleByCode(RoleCode code);
}
