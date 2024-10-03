package kz.bloooom.administration.domain.dto.role;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleMappingDto {
    List<RoleRepresentationDto> realmMappings;
}
