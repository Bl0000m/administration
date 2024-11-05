package kz.bloooom.administration.domain.entity;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "companies")
@FieldNameConstants
@FieldDefaults(level = AccessLevel.PRIVATE)
@TypeDef(
        name = "list-array",
        typeClass = ListArrayType.class
)
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "bin")
    String bin;

    @Column(name = "name")
    String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_type_id")
    CompanyType companyType;

    @Column(name = "address")
    String address;

    @Type(type = "list-array")
    @Column(name = "additional_address")
    List<String> additionalAddress;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "email")
    String email;

    @Column(name = "website")
    String website;

    @Column(name = "social_media")
    String socialMedia;

    @Column(name = "contract_id")
    String contractID;

    @Column(name = "created_date")
    @CreatedDate
    Timestamp createdDate;

    @Column(name = "updated_date")
    @LastModifiedDate
    Timestamp updatedDate;

    @Column(name = "is_deleted")
    boolean isDeleted;
}
