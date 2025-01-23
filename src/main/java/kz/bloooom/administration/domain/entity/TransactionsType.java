package kz.bloooom.administration.domain.entity;

import kz.bloooom.administration.enumeration.transaction_type.TransactionsTypeCode;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "transactions_type")
@FieldNameConstants
public class TransactionsType extends AbstractEnumEntity<TransactionsTypeCode> {
}
