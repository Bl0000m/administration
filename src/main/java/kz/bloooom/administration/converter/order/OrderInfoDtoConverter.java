package kz.bloooom.administration.converter.order;

import kz.bloooom.administration.converter.address.OrderAddressInfoDtoConverter;
import kz.bloooom.administration.converter.bouquet.BouquetInfoDtoConverter;
import kz.bloooom.administration.converter.branch_division.BranchDivisionInfoDtoConverter;
import kz.bloooom.administration.domain.dto.order.OrderInfoDto;
import kz.bloooom.administration.domain.entity.Order;
import kz.bloooom.administration.domain.entity.OrderAddress;
import kz.bloooom.administration.service.OrderAddressService;
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
    OrderAddressService orderAddressService;
    BranchDivisionInfoDtoConverter branchDivisionInfoDtoConverter;
    OrderAddressInfoDtoConverter orderAddressInfoDtoConverter;

    public OrderInfoDto convert(Order source) {
        OrderInfoDto target = new OrderInfoDto();
        target.setId(source.getId());
        target.setOrderCode(source.getOrderCode());
        OrderAddress orderAddress = orderAddressService.getByOrderId(source.getId());
        target.setAddress(Objects.nonNull(orderAddress) ? orderAddressInfoDtoConverter.convert(orderAddress) : null);
        target.setBouquetInfo(Objects.nonNull(source.getBouquet()) ?
                bouquetInfoDtoConverter.convertWithOutPrice(source.getBouquet()) : null);
        target.setAssemblyCost(source.getAssemblyCost());
        target.setBranchDivisionInfoDto(Objects.nonNull(source.getBranchDivision()) ?
                branchDivisionInfoDtoConverter.convertWithOutPrice(source.getBranchDivision()) : null);
        target.setDeliveryDate(source.getDeliveryDate());
        target.setDeliveryStartTime(source.getDeliveryStartTime());
        target.setDeliveryEndTime(source.getDeliveryEndTime());
        target.setOrderStatus(Objects.nonNull(source.getOrderStatus()) ?
                source.getOrderStatus().getName().get("ru") : null);
        return target;
    }

    public List<OrderInfoDto> convert(List<Order> orders) {
        return CollectionUtils.isEmpty(orders) ?
                Collections.emptyList() :
                orders.stream().map(this::convert).collect(Collectors.toList());
    }
}
