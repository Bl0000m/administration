package kz.bloooom.administration.facade.impl;

import kz.bloooom.administration.converter.role.RoleConverter;
import kz.bloooom.administration.domain.dto.role.RoleDto;
import kz.bloooom.administration.enumeration.role.RoleCode;
import kz.bloooom.administration.facade.RoleFacade;
import kz.bloooom.administration.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleFacadeImpl implements RoleFacade {
    RoleService roleService;
    RoleConverter roleConverter;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<RoleDto> getAllRoles() {
        return roleConverter.convert(roleService.getAllRoles());
    }

    @Override
    @Transactional(readOnly = true)
    public RoleDto getRoleById(Long id) {
        return roleConverter.convert(roleService.getById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public RoleDto getRoleByCode(RoleCode code) {
        return roleConverter.convert(roleService.getByCode(code));
    }
}
