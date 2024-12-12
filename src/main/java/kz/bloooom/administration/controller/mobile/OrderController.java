package kz.bloooom.administration.controller.mobile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bloooom.administration.domain.dto.order.OrderFillDto;
import kz.bloooom.administration.domain.dto.order.OrderInfoDto;
import kz.bloooom.administration.facade.OrderFacade;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/client/order")
@Tag(name = "Order Mobile API", description = "Методы для работы с заказами в мобильном приложении")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderController {

    OrderFacade orderFacade;

    @PostMapping
    @Operation(summary = "Заполнение заказа")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Void> fill(@Valid @RequestBody OrderFillDto orderFillDto) {
        log.info("POST: /v1/client/order fill order");
        orderFacade.fillOrder(orderFillDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/subscription/{subscriptionId}")
    @Operation(summary = "Получить все заказы по id подписки")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<OrderInfoDto>> getOrdersBySubscriptionId(@PathVariable("subscriptionId") Long subscriptionId) {
        log.info("GET: /v1/client/subscription/{}", subscriptionId);
        List<OrderInfoDto> orders = orderFacade.getOrdersBySubscriptionId(subscriptionId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(orders);
    }
}
