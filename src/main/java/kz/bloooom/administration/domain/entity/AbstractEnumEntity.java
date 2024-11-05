package kz.bloooom.administration.domain.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@MappedSuperclass
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public abstract class AbstractEnumEntity<E extends Enum<E>> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated(EnumType.STRING)
    E code;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", name = "name")
    Map<String, String> name;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", name = "description")
    Map<String, String> description;
}

