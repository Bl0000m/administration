package kz.bloooom.administration.facade;


import kz.bloooom.administration.domain.dto.AbstractEnumDto;
import kz.bloooom.administration.enumeration.subscription_status.SubscriptionStatusCode;

import java.util.List;

public interface SubscriptionStatusFacade {
    List<AbstractEnumDto<SubscriptionStatusCode>> getAllSubscriptionStatuses();

    AbstractEnumDto<SubscriptionStatusCode> getSubscriptionStatusById(Long id);

    AbstractEnumDto<SubscriptionStatusCode> getSubscriptionStatusByCode(SubscriptionStatusCode code);
}
