package kz.bloooom.administration.facade.impl;

import kz.bloooom.administration.converter.subscription_type.SubscriptionTypeConverter;
import kz.bloooom.administration.domain.dto.AbstractEnumDto;
import kz.bloooom.administration.enumeration.subscription_type.SubscriptionTypeCode;
import kz.bloooom.administration.facade.SubscriptionTypeFacade;
import kz.bloooom.administration.service.SubscriptionTypeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SubscriptionTypeFacadeImpl implements SubscriptionTypeFacade {
    SubscriptionTypeService subscriptionTypeService;
    SubscriptionTypeConverter subscriptionTypeConverter;

    @Override
    public List<AbstractEnumDto<SubscriptionTypeCode>> getAllSubscriptionTypes() {
        return subscriptionTypeConverter.convert(subscriptionTypeService.getAllSubscriptionTypes());
    }

    @Override
    public AbstractEnumDto<SubscriptionTypeCode> getSubscriptionTypeById(Long id) {
        return subscriptionTypeConverter.convert(subscriptionTypeService.getById(id));
    }

    @Override
    public AbstractEnumDto<SubscriptionTypeCode> getSubscriptionTypeByCode(SubscriptionTypeCode code) {
        return subscriptionTypeConverter.convert(subscriptionTypeService.getByCode(code));
    }
}
