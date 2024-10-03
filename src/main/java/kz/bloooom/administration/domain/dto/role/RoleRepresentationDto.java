package kz.bloooom.administration.domain.dto.role;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Map;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleRepresentationDto {
    String id;
    String name;
    String description;
    boolean composite;
    Boolean clientRole;
    String containerId;
    Map<String, List<String>> attributes;
}
