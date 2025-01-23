package kz.bloooom.administration.domain.entity;

import kz.bloooom.administration.enumeration.Currency;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
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
@Table(name = "balances")
@FieldNameConstants
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Balances {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    User user;

    @Column(name = "current_balance")
    Double currentBalance;

    @Column(name = "currency")
    Currency currency;

    @Column(name = "updated_date")
    @LastModifiedDate
    Timestamp updatedDate;
}
