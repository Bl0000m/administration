package kz.bloooom.administration.domain.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "bouquet_additional_elements")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BouquetAdditionalElements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    Bouquet bouquet;

    @ManyToOne
    AdditionalElements additionalElements;

    @Column(name = "quantity")
    int quantity;

    @Column(name = "color")
    String color;
}
