package kz.bloooom.administration.facade.impl;

import kz.bloooom.administration.converter.role.RoleConverter;
import kz.bloooom.administration.domain.dto.AbstractEnumDto;
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
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleFacadeImpl implements RoleFacade {
    RoleService roleService;
    RoleConverter roleConverter;

    @Override
    public List<AbstractEnumDto<RoleCode>> getAllRoles() {
        return roleConverter.convert(roleService.getAllRoles());
    }

    @Override
    public AbstractEnumDto<RoleCode> getRoleById(Long id) {
        return roleConverter.convert(roleService.getById(id));
    }

    @Override
    public AbstractEnumDto<RoleCode> getRoleByCode(RoleCode code) {
        return roleConverter.convert(roleService.getByCode(code));
    }
}
