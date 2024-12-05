package kz.bloooom.administration.controller.mobile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bloooom.administration.domain.dto.subscription.SubscriptionCreateDto;
import kz.bloooom.administration.domain.dto.subscription.SubscriptionShortInfoDto;
import kz.bloooom.administration.domain.dto.user.UserRegistrationDto;
import kz.bloooom.administration.facade.SubscriptionFacade;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/client/subscription")
@Tag(name = "Subscription Mobile API", description = "Методы для работы с подписками в мобильном приложении")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SubscriptionMobileController {

    SubscriptionFacade subscriptionFacade;

    @PostMapping
    @Operation(summary = "Создание подписки")
    public ResponseEntity<SubscriptionShortInfoDto> create(@Valid @RequestBody SubscriptionCreateDto dto) {
        log.info("POST: /v1/client/subscription create subscription for user id: {}", dto.getUserId());
        subscriptionFacade.create(dto);
        return ResponseEntity.ok().build();
    }
}
