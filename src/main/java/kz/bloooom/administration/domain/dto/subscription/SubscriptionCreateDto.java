package kz.bloooom.administration.domain.dto.subscription;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Обьект для создания подписки")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubscriptionCreateDto {
    @NotNull(message = "Id юзера не должен быть пустым")
    @Schema(description = "Id юзера")
    Long userId;

    @NotNull(message = "Id типа подписки не должен быть пустым")
    @Schema(description = "Id типа подписки")
    Long subscriptionTypeId;

    @Schema(description = "Время начала подписки", example = "2022-06-30 11:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime startTime;

    @Schema(description = "Время завершения подписки", example = "2022-06-30 11:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime endTime;

    @Schema(description = "Время заказов", example = "2022-06-30 11:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    List<LocalDateTime> orderDates;
}
