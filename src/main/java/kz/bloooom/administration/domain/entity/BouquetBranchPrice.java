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
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "bouquet_branch_price")
@FieldNameConstants
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BouquetBranchPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bouquet_id")
    Bouquet bouquet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_division_id")
    BranchDivision branchDivision;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    Employee employee;

    @Column(name = "price")
    Double price;
}
