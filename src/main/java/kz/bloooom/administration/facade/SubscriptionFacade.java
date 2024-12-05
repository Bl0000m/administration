package kz.bloooom.administration.facade;

import kz.bloooom.administration.domain.dto.subscription.SubscriptionCreateDto;
import kz.bloooom.administration.domain.dto.subscription.SubscriptionShortInfoDto;

public interface SubscriptionFacade {
    SubscriptionShortInfoDto create(SubscriptionCreateDto dto);
}
