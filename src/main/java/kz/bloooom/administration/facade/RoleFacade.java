package kz.bloooom.administration.facade;


import kz.bloooom.administration.domain.dto.role.RoleDto;
import kz.bloooom.administration.enumeration.role.RoleCode;

import java.util.List;

public interface RoleFacade {
    List<RoleDto> getAllRoles();

    RoleDto getRoleById(Long id);

    RoleDto getRoleByCode(RoleCode code);
}
