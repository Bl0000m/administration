package kz.bloooom.administration.domain.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import kz.bloooom.administration.domain.dto.subscription.SubscriptionShortInfoForUserDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@Schema(description = "Объект передачи данных для получения информации о подписках пользователя")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserSubscriptionsInfoDto {

    @Schema(description = "Подписки пользователя")
    Set<SubscriptionShortInfoForUserDto> subscriptions;
}
