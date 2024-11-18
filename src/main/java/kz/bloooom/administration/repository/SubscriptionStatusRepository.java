package kz.bloooom.administration.repository;

import kz.bloooom.administration.domain.entity.SubscriptionStatus;
import kz.bloooom.administration.enumeration.subscription_status.SubscriptionStatusCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionStatusRepository extends JpaRepository<SubscriptionStatus, Long> {
    Optional<SubscriptionStatus> getByCode(SubscriptionStatusCode subscriptionStatusCode);
}
