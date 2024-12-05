package kz.bloooom.administration.domain.entity;

import kz.bloooom.administration.enumeration.order_status.OrderStatusCode;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "order_status")
@FieldNameConstants
public class OrderStatus extends AbstractEnumEntity<OrderStatusCode> {

}

