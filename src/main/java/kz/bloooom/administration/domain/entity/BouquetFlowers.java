package kz.bloooom.administration.domain.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "bouquet_flowers")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BouquetFlowers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    Bouquet bouquet;

    @ManyToOne
    Flower flower;

    int quantity;
}
