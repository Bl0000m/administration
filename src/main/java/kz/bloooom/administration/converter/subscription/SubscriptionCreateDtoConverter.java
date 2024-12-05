package kz.bloooom.administration.converter.subscription;

import kz.bloooom.administration.domain.dto.subscription.SubscriptionCreateDto;
import kz.bloooom.administration.domain.entity.Subscription;
import kz.bloooom.administration.enumeration.subscription_status.SubscriptionStatusCode;
import kz.bloooom.administration.service.SubscriptionStatusService;
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
    SubscriptionStatusService subscriptionStatusService;

    public Subscription convert(SubscriptionCreateDto source) {
        Subscription target = new Subscription();
        target.setUser(userService.getById(source.getUserId()));
        target.setName(source.getName());
        target.setSubscriptionType(subscriptionTypeService.getById(source.getSubscriptionTypeId()));
        target.setSubscriptionStatus(subscriptionStatusService.getByCode(SubscriptionStatusCode.NOT_ACTIVE));
        target.setStartTime(source.getOrderDates().get(0));
        target.setEndTime(source.getOrderDates().get(source.getOrderDates().size() - 1));
        target.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        target.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
        return target;
    }
}
