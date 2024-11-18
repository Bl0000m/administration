package kz.bloooom.administration.facade.impl;

import kz.bloooom.administration.converter.subscription.SubscriptionCreateDtoConverter;
import kz.bloooom.administration.domain.dto.subscription.SubscriptionCreateDto;
import kz.bloooom.administration.domain.entity.Subscription;
import kz.bloooom.administration.facade.SubscriptionFacade;
import kz.bloooom.administration.service.SubscriptionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SubscriptionFacadeImpl implements SubscriptionFacade {
    SubscriptionService subscriptionService;
    SubscriptionCreateDtoConverter subscriptionCreateDtoConverter;

    @Override
    @Transactional
    public void create(SubscriptionCreateDto dto) {
        Subscription subscription = subscriptionCreateDtoConverter.convert(dto);
        subscriptionService.save(subscription);
    }
}
