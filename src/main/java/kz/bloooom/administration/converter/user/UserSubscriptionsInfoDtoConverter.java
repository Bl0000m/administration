package kz.bloooom.administration.converter.user;

import kz.bloooom.administration.domain.dto.subscription.SubscriptionShortInfoForUserDto;
import kz.bloooom.administration.domain.dto.user.UserSubscriptionsInfoDto;
import kz.bloooom.administration.domain.entity.Subscription;
import kz.bloooom.administration.domain.entity.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserSubscriptionsInfoDtoConverter {

    public UserSubscriptionsInfoDto convert(User source) {
        UserSubscriptionsInfoDto target = new UserSubscriptionsInfoDto();

        Set<SubscriptionShortInfoForUserDto> mySubscriptions = source.getSubscriptions()
                .stream()
                .map(this::convertSubscriptionToShortInfo)
                .collect(Collectors.toSet());

        target.setSubscriptions(mySubscriptions);

        return target;
    }

    private SubscriptionShortInfoForUserDto convertSubscriptionToShortInfo(Subscription subscription) {
        return SubscriptionShortInfoForUserDto.builder()
                .id(subscription.getId())
                .name(subscription.getName())
                .type(subscription.getSubscriptionType().getName().get("ru"))
                .startDate(subscription.getStartTime())
                .endDate(subscription.getEndTime())
                .status(subscription.getSubscriptionStatus().getName().get("ru"))
                .build();
    }
}
