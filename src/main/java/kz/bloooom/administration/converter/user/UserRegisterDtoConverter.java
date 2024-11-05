package kz.bloooom.administration.converter.user;

import kz.bloooom.administration.domain.dto.user.UserRegistrationDto;
import kz.bloooom.administration.domain.entity.User;
import kz.bloooom.administration.enumeration.role.RoleCode;
import kz.bloooom.administration.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserRegisterDtoConverter {

    RoleService roleService;
    public User convert(UserRegistrationDto source) {
        User target = new User();
        target.setName(source.getName());
        target.setEmail(source.getEmail());
        target.setPhoneNumber(source.getPhoneNumber());
        target.setRole(roleService.getByCode(RoleCode.CLIENT));
        target.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        target.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
        target.setDeleted(false);
        return target;
    }
}
