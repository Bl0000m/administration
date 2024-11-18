package kz.bloooom.administration.facade.impl;

import kz.bloooom.administration.converter.subscription_status.SubscriptionStatusConverter;
import kz.bloooom.administration.domain.dto.AbstractEnumDto;
import kz.bloooom.administration.enumeration.subscription_status.SubscriptionStatusCode;
import kz.bloooom.administration.facade.SubscriptionStatusFacade;
import kz.bloooom.administration.service.SubscriptionStatusService;
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
public class SubscriptionStatusFacadeImpl implements SubscriptionStatusFacade {

    SubscriptionStatusService subscriptionStatusService;
    SubscriptionStatusConverter subscriptionStatusConverter;

    @Override
    public List<AbstractEnumDto<SubscriptionStatusCode>> getAllSubscriptionStatuses() {
        return subscriptionStatusConverter.convert(subscriptionStatusService.getAllSubscriptionStatuses());
    }

    @Override
    public AbstractEnumDto<SubscriptionStatusCode> getSubscriptionStatusById(Long id) {
        return subscriptionStatusConverter.convert(subscriptionStatusService.getById(id));
    }

    @Override
    public AbstractEnumDto<SubscriptionStatusCode> getSubscriptionStatusByCode(SubscriptionStatusCode code) {
        return subscriptionStatusConverter.convert(subscriptionStatusService.getByCode(code));
    }
}
