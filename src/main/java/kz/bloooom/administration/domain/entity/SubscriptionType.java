package kz.bloooom.administration.domain.entity;

import kz.bloooom.administration.enumeration.subscription_type.SubscriptionTypeCode;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "subscription_type")
@FieldNameConstants
public class SubscriptionType extends AbstractEnumEntity<SubscriptionTypeCode> {

}

