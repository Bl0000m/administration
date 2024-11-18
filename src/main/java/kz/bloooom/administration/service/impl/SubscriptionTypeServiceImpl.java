package kz.bloooom.administration.service.impl;

import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.domain.entity.SubscriptionType;
import kz.bloooom.administration.enumeration.subscription_type.SubscriptionTypeCode;
import kz.bloooom.administration.exception.BloomAdministrationException;
import kz.bloooom.administration.repository.SubscriptionTypeRepository;
import kz.bloooom.administration.service.SubscriptionTypeService;
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
public class SubscriptionTypeServiceImpl implements SubscriptionTypeService {
    SubscriptionTypeRepository subscriptionTypeRepository;

    @Override
    public SubscriptionType getById(Long id) {
        return subscriptionTypeRepository.findById(id)
                .orElseThrow(() -> new BloomAdministrationException(
                        HttpStatus.NOT_FOUND,
                        ErrorCodeConstant.SUBSCRIPTION_TYPE_WITH_THIS_ID_DOEST_EXISTS,
                        "messages.exception.subscription-type-not-found", id));
    }

    @Override
    public List<SubscriptionType> getAllSubscriptionTypes() {
        return subscriptionTypeRepository.findAll();
    }

    @Override
    public SubscriptionType getByCode(SubscriptionTypeCode subscriptionTypeCode) {
        return subscriptionTypeRepository.getByCode(subscriptionTypeCode)
                .orElseThrow(() -> new BloomAdministrationException(
                        HttpStatus.NOT_FOUND,
                        ErrorCodeConstant.SUBSCRIPTION_TYPE_WITH_THIS_CODE_DOEST_EXISTS,
                        "messages.exception.subscription-type-code-not-found", subscriptionTypeCode));
    }
}
