package kz.bloooom.administration.controller.mobile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bloooom.administration.domain.dto.AbstractEnumDto;
import kz.bloooom.administration.enumeration.subscription_status.SubscriptionStatusCode;
import kz.bloooom.administration.facade.SubscriptionStatusFacade;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/client/subscription-status")
@Tag(name = "Subscription Status API", description = "Методы для работы с статусами подписок")
@SecurityRequirement(name = "Bearer Authentication")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SubscriptionStatusController {

    SubscriptionStatusFacade subscriptionStatusFacade;

    @GetMapping()
    @Operation(summary = "Получить список всех статусов подписок")
    @SecurityRequirement(name = "Bearer Authentication")
    public List<AbstractEnumDto<SubscriptionStatusCode>> getAllSubscriptionStatuses() {
        return subscriptionStatusFacade.getAllSubscriptionStatuses();
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Получить статус подписки по id")
    @SecurityRequirement(name = "Bearer Authentication")
    public AbstractEnumDto<SubscriptionStatusCode> getSubscriptionStatusById(@PathVariable Long id) {
        return subscriptionStatusFacade.getSubscriptionStatusById(id);
    }

    @GetMapping("/code/{code}")
    @Operation(summary = "Получить статус подписки по коду")
    @SecurityRequirement(name = "Bearer Authentication")
    public AbstractEnumDto<SubscriptionStatusCode> getSubscriptionTypeByCode(@PathVariable SubscriptionStatusCode code) {
        return subscriptionStatusFacade.getSubscriptionStatusByCode(code);
    }
}
