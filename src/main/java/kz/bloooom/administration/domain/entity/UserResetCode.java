package kz.bloooom.administration.domain.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_reset_code")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserResetCode  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "email")
    String email;

    @Column(name = "reset_code")
    String resetCode;

    @Column(name = "created_date")
    @CreatedDate
    Timestamp createdDate;

    @Column(name = "updated_date")
    @LastModifiedDate
    Timestamp updatedDate;
}