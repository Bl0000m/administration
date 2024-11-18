package kz.bloooom.administration.facade;


import kz.bloooom.administration.domain.dto.AbstractEnumDto;
import kz.bloooom.administration.enumeration.subscription_type.SubscriptionTypeCode;

import java.util.List;

public interface SubscriptionTypeFacade {
    List<AbstractEnumDto<SubscriptionTypeCode>> getAllSubscriptionTypes();

    AbstractEnumDto<SubscriptionTypeCode> getSubscriptionTypeById(Long id);

    AbstractEnumDto<SubscriptionTypeCode> getSubscriptionTypeByCode(SubscriptionTypeCode code);
}
