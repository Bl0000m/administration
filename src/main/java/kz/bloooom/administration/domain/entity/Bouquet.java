package kz.bloooom.administration.domain.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "bouquet")
@FieldNameConstants
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Bouquet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bouquet_styles_id")
    BouquetStyle bouquetStyle;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bouquet", cascade = CascadeType.ALL)
    Set<BouquetPhoto> bouquetPhotos;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "bouquet_flower_variety",
            joinColumns = @JoinColumn(name = "bouquet_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "flower_variety_id", referencedColumnName = "id"))
    Set<FlowerVariety> flowers = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "bouquet_additional_elements",
            joinColumns = @JoinColumn(name = "bouquet_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "additional_elements_id", referencedColumnName = "id"))
    Set<AdditionalElements> additionalElements = new HashSet<>();

    @Column(name = "created_date")
    @CreatedDate
    Timestamp createdDate;

    @Column(name = "updated_date")
    @LastModifiedDate
    Timestamp updatedDate;
}
