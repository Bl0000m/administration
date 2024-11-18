package kz.bloooom.administration.repository;

import kz.bloooom.administration.domain.entity.SubscriptionType;
import kz.bloooom.administration.enumeration.subscription_type.SubscriptionTypeCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionTypeRepository extends JpaRepository<SubscriptionType, Long> {
    Optional<SubscriptionType> getByCode(SubscriptionTypeCode subscriptionTypeCode);
}
