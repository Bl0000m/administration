package kz.bloooom.administration.converter.subscription;

import kz.bloooom.administration.domain.dto.subscription.SubscriptionCreateDto;
import kz.bloooom.administration.domain.entity.Subscription;
import kz.bloooom.administration.service.SubscriptionTypeService;
import kz.bloooom.administration.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SubscriptionCreateDtoConverter {

    UserService userService;
    SubscriptionTypeService subscriptionTypeService;

    public Subscription convert(SubscriptionCreateDto source) {
        Subscription target = new Subscription();
        target.setUser(userService.getById(source.getUserId()));
        target.setSubscriptionType(subscriptionTypeService.getById(source.getSubscriptionTypeId()));
        target.setStartTime(source.getStartTime());
        target.setEndTime(source.getEndTime());
        target.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        target.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
        return target;
    }
}
