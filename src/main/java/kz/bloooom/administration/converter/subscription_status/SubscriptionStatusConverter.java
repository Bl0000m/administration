package kz.bloooom.administration.converter.subscription_status;


import kz.bloooom.administration.converter.AbstractEnumDtoConverter;
import kz.bloooom.administration.domain.entity.SubscriptionStatus;
import kz.bloooom.administration.enumeration.subscription_status.SubscriptionStatusCode;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionStatusConverter
        extends AbstractEnumDtoConverter<SubscriptionStatusCode, SubscriptionStatus> {
}
