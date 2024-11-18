package kz.bloooom.administration.converter.subscription_type;


import kz.bloooom.administration.converter.AbstractEnumDtoConverter;
import kz.bloooom.administration.domain.entity.SubscriptionType;
import kz.bloooom.administration.enumeration.subscription_type.SubscriptionTypeCode;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionTypeConverter
        extends AbstractEnumDtoConverter<SubscriptionTypeCode, SubscriptionType> {
}
