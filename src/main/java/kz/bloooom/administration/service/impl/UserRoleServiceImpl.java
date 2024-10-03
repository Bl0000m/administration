package kz.bloooom.administration.service.impl;

import kz.bloooom.administration.domain.entity.Role;
import kz.bloooom.administration.domain.entity.User;
import kz.bloooom.administration.domain.entity.UserRole;
import kz.bloooom.administration.repository.UserRoleRepository;
import kz.bloooom.administration.service.UserRoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserRoleServiceImpl implements UserRoleService {

    UserRoleRepository userRoleRepository;

    @Override
    @Transactional
    public void save(User user, List<Role> roles) {
        List<UserRole> userRoles = roles.stream()
                .map(role -> new UserRole(user, role))
                .collect(Collectors.toList());

        userRoleRepository.saveAll(userRoles);
    }


}
