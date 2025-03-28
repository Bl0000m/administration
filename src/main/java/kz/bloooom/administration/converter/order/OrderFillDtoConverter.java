package kz.bloooom.administration.converter.order;

import kz.bloooom.administration.domain.dto.order.OrderFillDto;
import kz.bloooom.administration.domain.entity.Order;
import kz.bloooom.administration.domain.entity.OrderCode;
import kz.bloooom.administration.enumeration.order_status.OrderStatusCode;
import kz.bloooom.administration.service.BouquetService;
import kz.bloooom.administration.service.BranchDivisionService;
import kz.bloooom.administration.service.OrderCodeService;
import kz.bloooom.administration.service.OrderStatusService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderFillDtoConverter {

    OrderStatusService orderStatusService;
    OrderCodeService orderCodeService;
    BouquetService bouquetService;
    BranchDivisionService branchDivisionService;

    public Order convert(OrderFillDto source, Order target) {

        target.setOrderStatus(orderStatusService.getByCode(OrderStatusCode.NEW));
        target.setOrderCode(generateUniqueOrderCode());
        target.setAssemblyCost(source.getAssemblyCost());
        target.setBouquet(bouquetService.getById(source.getBouquetId()));
        target.setBranchDivision(branchDivisionService.getById(source.getBranchDivisionId()));
        return target;
    }

    public Long generateUniqueOrderCode() {
        Random random = new Random();
        Long code;

        do {
            code = (long) (10000000 + random.nextInt(90000000));
        } while (orderCodeService.existsByCode(code));

        orderCodeService.save(new OrderCode(code));
        return code;
    }
}
