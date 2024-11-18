package kz.bloooom.administration.controller.mobile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bloooom.administration.domain.dto.AbstractEnumDto;
import kz.bloooom.administration.enumeration.subscription_type.SubscriptionTypeCode;
import kz.bloooom.administration.facade.SubscriptionTypeFacade;
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
@RequestMapping("/v1/client/subscription-type")
@Tag(name = "Subscription Type API", description = "Методы для работы с типами подписок")
@SecurityRequirement(name = "Bearer Authentication")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SubscriptionTypeController {

    SubscriptionTypeFacade subscriptionTypeFacade;

    @GetMapping()
    @Operation(summary = "Получить список всех типов подписок")
    public List<AbstractEnumDto<SubscriptionTypeCode>> getAllSubscriptionTypes() {
        return subscriptionTypeFacade.getAllSubscriptionTypes();
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Получить тип подписки по id")
    public AbstractEnumDto<SubscriptionTypeCode> getSubscriptionTypeById(@PathVariable Long id) {
        return subscriptionTypeFacade.getSubscriptionTypeById(id);
    }

    @GetMapping("/code/{code}")
    @Operation(summary = "Получить тип подписки по коду")
    public AbstractEnumDto<SubscriptionTypeCode> getSubscriptionTypeByCode(@PathVariable SubscriptionTypeCode code) {
        return subscriptionTypeFacade.getSubscriptionTypeByCode(code);
    }
}
