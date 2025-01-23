package kz.bloooom.administration.domain.entity;

import kz.bloooom.administration.enumeration.Currency;
import kz.bloooom.administration.enumeration.transaction_type.TransactionsTypeCode;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "transactions")
@FieldNameConstants
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    User user;

    @Column(name = "amount")
    Double amount;

    @Column(name = "currency")
    Currency currency;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "transactions_type_id")
    TransactionsType type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "transactions_status_id")
    TransactionsStatus status;

    @Column(name = "description")
    String description;

    @Column(name = "created_date")
    @CreatedDate
    Timestamp createdDate;

    @Column(name = "updated_date")
    @LastModifiedDate
    Timestamp updatedDate;
}
