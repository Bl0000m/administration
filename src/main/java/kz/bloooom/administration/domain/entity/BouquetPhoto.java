package kz.bloooom.administration.domain.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
@Table(name = "bouquet_photos")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BouquetPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "title")
    String title;

    @Column(name = "directory")
    String directory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bouquet_id")
    Bouquet bouquet;

    @Column(name = "uploaded_by")
    String uploadedBy;

    @Column(name = "created_date")
    @CreatedDate
    Timestamp createdDate;
}
