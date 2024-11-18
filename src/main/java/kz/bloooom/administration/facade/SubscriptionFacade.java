package kz.bloooom.administration.facade;

import kz.bloooom.administration.domain.dto.subscription.SubscriptionCreateDto;

public interface SubscriptionFacade {
    void create(SubscriptionCreateDto dto);
}
