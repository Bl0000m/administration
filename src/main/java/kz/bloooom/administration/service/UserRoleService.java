package kz.bloooom.administration.service;

import kz.bloooom.administration.domain.entity.Role;
import kz.bloooom.administration.domain.entity.User;

import java.util.List;

public interface UserRoleService {
    void save(User user, List<Role> roles);
}
