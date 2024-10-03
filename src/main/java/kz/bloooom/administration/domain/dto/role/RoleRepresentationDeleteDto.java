package kz.bloooom.administration.domain.dto.role;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@ToString
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleRepresentationDeleteDto {
    String id;
    String name;
}
