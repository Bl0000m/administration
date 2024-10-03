package kz.bloooom.administration.converter.role;



import kz.bloooom.administration.domain.dto.role.RoleDto;
import kz.bloooom.administration.domain.entity.Role;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleConverter {

    public RoleDto convert(Role source) {
        RoleDto target = new RoleDto();
        target.setId(source.getId());
        target.setCode(source.getCode());
        target.setName(source.getName().get("ru"));
        target.setDescription(source.getDescription().get("ru"));
        return target;
    }

    public List<RoleDto> convert(List<Role> source) {
        return CollectionUtils.isEmpty(source) ?
                Collections.emptyList() :
                source.stream()
                        .map(this::convert)
                        .collect(Collectors.toList());
    }
}
