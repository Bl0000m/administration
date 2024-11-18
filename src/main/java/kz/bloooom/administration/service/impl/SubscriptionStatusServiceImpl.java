package kz.bloooom.administration.service.impl;

import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.domain.entity.SubscriptionStatus;
import kz.bloooom.administration.enumeration.subscription_status.SubscriptionStatusCode;
import kz.bloooom.administration.exception.BloomAdministrationException;
import kz.bloooom.administration.repository.SubscriptionStatusRepository;
import kz.bloooom.administration.service.SubscriptionStatusService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SubscriptionStatusServiceImpl implements SubscriptionStatusService {
    SubscriptionStatusRepository subscriptionStatusRepository;

    @Override
    public SubscriptionStatus getById(Long id) {
        return subscriptionStatusRepository.findById(id)
                .orElseThrow(() -> new BloomAdministrationException(
                        HttpStatus.NOT_FOUND,
                        ErrorCodeConstant.SUBSCRIPTION_STATUS_WITH_THIS_ID_DOEST_EXISTS,
                        "messages.exception.subscription-status-not-found", id));
    }

    @Override
    public List<SubscriptionStatus> getAllSubscriptionStatuses() {
        return subscriptionStatusRepository.findAll();
    }

    @Override
    public SubscriptionStatus getByCode(SubscriptionStatusCode subscriptionStatusCode) {
        return subscriptionStatusRepository.getByCode(subscriptionStatusCode)
                .orElseThrow(() -> new BloomAdministrationException(
                        HttpStatus.NOT_FOUND,
                        ErrorCodeConstant.SUBSCRIPTION_STATUS_WITH_THIS_CODE_DOEST_EXISTS,
                        "messages.exception.subscription-status-code-not-found", subscriptionStatusCode));
    }
}
