package kz.bloooom.administration.service;

import kz.bloooom.administration.domain.entity.Role;
import kz.bloooom.administration.enumeration.role.RoleCode;

import java.util.List;

public interface RoleService {
    Role getById(Long id);

    List<Role> getAllRoles();

    Role getByCode(RoleCode roleCode);
}
