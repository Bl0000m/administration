package kz.bloooom.administration.domain.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import kz.bloooom.administration.enumeration.role.RoleCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Map;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "role")
@FieldNameConstants
@FieldDefaults(level = AccessLevel.PRIVATE)
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated(EnumType.STRING)
    RoleCode code;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", name = "name")
    Map<String, String> name;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", name = "description")
    Map<String, String> description;
}
