package kz.bloooom.administration.domain.entity;

import kz.bloooom.administration.enumeration.Fragrance;
import kz.bloooom.administration.enumeration.Season;
import kz.bloooom.administration.enumeration.StemType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "flower_variety")
@FieldNameConstants
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlowerVariety {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flower_id")
    Flower flower;

    @Column(name = "shelf_life_days_min")
    Long shelfLifeDaysMin;

    @Column(name = "shelf_life_days_max")
    Long shelfLifeDaysMax;

    @Column(name = "fragrance")
    Fragrance fragrance;

    @Column(name = "season")
    Season season;

    @Column(name = "steam_type")
    StemType steamType;

    @Column(name = "bud_size_min")
    Double budSizeMin;

    @Column(name = "bud_size_max")
    Double budSizeMax;

    @Column(name = "stem_height_size_min")
    Double stemHeightSizeMin;

    @Column(name = "stem_height_size_max")
    Double stemHeightSizeMax;

    @Column(name = "image")
    String image;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stem_care_id")
    StemCare stemCare;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "temperature_care_id")
    TemperatureCare temperatureCare;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "water_care_id")
    WaterCare waterCare;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    Country country;

    @Column(name = "created_date")
    @CreatedDate
    Timestamp createdDate;

    @Column(name = "updated_date")
    @LastModifiedDate
    Timestamp updatedDate;

    @Column(name = "created_by")
    @CreatedBy
    String createdBy;

    @Column(name = "updated_by")
    @LastModifiedBy
    String updatedBy;
}
