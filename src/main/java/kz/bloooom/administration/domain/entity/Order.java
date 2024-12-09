package kz.bloooom.administration.domain.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "orders")
@FieldNameConstants
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "order_code")
    Long orderCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_id")
    Subscription subscription;

    @Column(name = "address")
    String address;

    @OneToOne
    @JoinColumn(name = "bouquet_id")
    Bouquet bouquet;

    @Column(name = "delivery_date")
    LocalDate deliveryDate;

    @Column(name = "delivery_start_time")
    LocalTime deliveryStartTime;

    @Column(name = "delivery_end_time")
    LocalTime deliveryEndTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_status_id")
    OrderStatus orderStatus;

    @Column(name = "created_date")
    @CreatedDate
    Timestamp createdDate;

    @Column(name = "updated_date")
    @LastModifiedDate
    Timestamp updatedDate;
}
