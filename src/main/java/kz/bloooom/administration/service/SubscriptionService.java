package kz.bloooom.administration.service;

import kz.bloooom.administration.domain.entity.Subscription;

public interface SubscriptionService {
    Subscription save(Subscription subscription);

    Subscription findById(Long id);
}
