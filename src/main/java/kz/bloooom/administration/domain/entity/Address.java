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
@Table(name = "address")
@FieldNameConstants
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "street")
    String street;

    @Column(name = "building")
    String building;

    @Column(name = "apartment")
    String apartment;

    @Column(name = "entrance")
    String entrance;

    @Column(name = "intercom")
    String intercom;

    @Column(name = "floor")
    Integer floor;

    @Column(name = "city")
    String city;

    @Column(name = "postal_code")
    String postalCode;

    @Column(name = "latitude")
    Double latitude;

    @Column(name = "longitude")
    Double longitude;
}
