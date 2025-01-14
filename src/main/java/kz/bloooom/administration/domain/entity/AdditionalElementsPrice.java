package kz.bloooom.administration.domain.entity;

import kz.bloooom.administration.enumeration.Currency;
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
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "additional_element_price")
@FieldNameConstants
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdditionalElementsPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "additional_elements_id")
    AdditionalElements additionalElements;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_division_id")
    BranchDivision branchDivision;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    Employee employee;

    @Column(name = "price")
    Double price;

    @Column(name = "currency")
    Currency currency;

    @Column(name = "valid_from")
    LocalDateTime validFrom;

    @Column(name = "valid_to")
    LocalDateTime validTo;

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
