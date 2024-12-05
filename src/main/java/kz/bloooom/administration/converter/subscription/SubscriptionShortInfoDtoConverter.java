package kz.bloooom.administration.converter.subscription;

import kz.bloooom.administration.domain.dto.subscription.SubscriptionShortInfoDto;
import kz.bloooom.administration.domain.entity.Subscription;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SubscriptionShortInfoDtoConverter {

    public SubscriptionShortInfoDto convert(Subscription source) {
        SubscriptionShortInfoDto target = new SubscriptionShortInfoDto();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setUserName(source.getUser().getName());
        return target;
    }
}
