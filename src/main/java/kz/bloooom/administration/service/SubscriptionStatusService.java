package kz.bloooom.administration.service;

import kz.bloooom.administration.domain.entity.SubscriptionStatus;
import kz.bloooom.administration.enumeration.subscription_status.SubscriptionStatusCode;

import java.util.List;

public interface SubscriptionStatusService {
    SubscriptionStatus getById(Long id);

    List<SubscriptionStatus> getAllSubscriptionStatuses();

    SubscriptionStatus getByCode(SubscriptionStatusCode subscriptionStatusCode);
}
