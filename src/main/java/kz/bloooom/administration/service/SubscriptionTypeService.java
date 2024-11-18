package kz.bloooom.administration.service;

import kz.bloooom.administration.domain.entity.SubscriptionType;
import kz.bloooom.administration.enumeration.subscription_type.SubscriptionTypeCode;

import java.util.List;

public interface SubscriptionTypeService {
    SubscriptionType getById(Long id);

    List<SubscriptionType> getAllSubscriptionTypes();

    SubscriptionType getByCode(SubscriptionTypeCode subscriptionTypeCode);
}
