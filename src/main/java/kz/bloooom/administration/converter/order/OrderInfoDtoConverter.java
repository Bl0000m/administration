package kz.bloooom.administration.converter.order;

import kz.bloooom.administration.converter.bouquet.BouquetInfoDtoConverter;
import kz.bloooom.administration.domain.dto.order.OrderInfoDto;
import kz.bloooom.administration.domain.entity.Order;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderInfoDtoConverter {

    BouquetInfoDtoConverter bouquetInfoDtoConverter;

    public OrderInfoDto convert(Order source) {
        OrderInfoDto target = new OrderInfoDto();
        target.setId(source.getId());
        target.setOrderCode(source.getOrderCode());
        target.setAddress(source.getAddress());
        target.setBouquetInfo(Objects.nonNull(source.getBouquet()) ?
                bouquetInfoDtoConverter.convert(source.getBouquet()) : null);
        target.setDeliveryDate(source.getDeliveryDate());
        target.setDeliveryStartTime(source.getDeliveryStartTime());
        target.setDeliveryEndTime(source.getDeliveryEndTime());
        target.setOrderStatus(source.getOrderStatus().getName().get("ru"));
        return target;
    }

    public List<OrderInfoDto> convert(List<Order> orders) {
        return CollectionUtils.isEmpty(orders) ?
                Collections.emptyList() :
                orders.stream().map(this::convert).collect(Collectors.toList());
    }
}
