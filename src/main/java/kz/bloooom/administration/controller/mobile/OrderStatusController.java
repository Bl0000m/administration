package kz.bloooom.administration.controller.mobile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bloooom.administration.domain.dto.AbstractEnumDto;
import kz.bloooom.administration.enumeration.order_status.OrderStatusCode;
import kz.bloooom.administration.facade.OrderStatusFacade;
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
@RequestMapping("/v1/order-status")
@Tag(name = "Status API", description = "Методы для работы с статусами заказа")
@SecurityRequirement(name = "Bearer Authentication")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderStatusController {

    OrderStatusFacade statusFacade;

    @GetMapping()
    @Operation(summary = "Получить список всех статусов заказа")
    @SecurityRequirement(name = "Bearer Authentication")
    public List<AbstractEnumDto<OrderStatusCode>> getAllStatuses() {
        return statusFacade.getAllStatuses();
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Получить статус заказа по id")
    @SecurityRequirement(name = "Bearer Authentication")
    public AbstractEnumDto<OrderStatusCode> getStatusById(@PathVariable Long id) {
        return statusFacade.getStatusById(id);
    }

    @GetMapping("/code/{code}")
    @Operation(summary = "Получить статус заказа по коду")
    @SecurityRequirement(name = "Bearer Authentication")
    public AbstractEnumDto<OrderStatusCode> getStatusByCode(@PathVariable OrderStatusCode code) {
        return statusFacade.getStatusByCode(code);
    }

}
