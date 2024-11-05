package kz.bloooom.administration.domain.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "employee")
@FieldNameConstants
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee extends AbstractUserEntity {

    @Column(name = "iin")
    String iin;

    @Column(name = "position")
    String position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    Company company;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id")
    Status status;
}
