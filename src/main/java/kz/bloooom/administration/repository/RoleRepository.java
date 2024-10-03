package kz.bloooom.administration.repository;

import kz.bloooom.administration.domain.entity.Role;
import kz.bloooom.administration.enumeration.role.RoleCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> getByCode(RoleCode roleCode);
}
