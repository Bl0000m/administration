package kz.bloooom.administration.domain.dto.subscription;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Schema(description = "Обьект для показа краткой информации")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubscriptionShortInfoForUserDto {
    @Schema(description = "Id подписки")
    Long id;

    @Schema(description = "Наименование подписки")
    String name;

    @Schema(description = "Тип подписки")
    String type;

    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDateTime startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDateTime endDate;

    String status;
}
