package kz.bloooom.administration.domain.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "additional_elements")
@FieldNameConstants
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdditionalElements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "additionalElements", cascade = CascadeType.ALL)
    Set<AdditionalElementsPrice> additionalElementsPrices;

    @Column(name = "description")
    String description;

    @Column(name = "example")
    String example;

    @Column(name = "unit_of_measurement")
    String unitOfMeasurement;

    @Column(name = "created_date")
    @CreatedDate
    Timestamp createdDate;

    @Column(name = "created_by")
    @CreatedBy
    String createdBy;
}
