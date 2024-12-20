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
@Table(name = "bouquet_flower_variety")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BouquetFlowerVariety {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    Bouquet bouquet;

    @ManyToOne
    FlowerVariety flowerVariety;

    int quantity;
}
