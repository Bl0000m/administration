package kz.bloooom.administration.facade.impl;

import kz.bloooom.administration.converter.address.AddressCreateDtoConverter;
import kz.bloooom.administration.converter.address.AddressInfoDtoConverter;
import kz.bloooom.administration.domain.dto.address.AddressInfoDto;
import kz.bloooom.administration.domain.dto.address.OrderAddressCreateDto;
import kz.bloooom.administration.domain.dto.address.UserAddressCreateDto;
import kz.bloooom.administration.domain.entity.*;
import kz.bloooom.administration.facade.AddressFacade;
import kz.bloooom.administration.service.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AddressFacadeImpl implements AddressFacade {

    AddressService addressService;
    UserAddressService userAddressService;
    OrderAddressService orderAddressService;
    OrderService orderService;
    UserService userService;
    AddressCreateDtoConverter addressCreateDtoConverter;
    AddressInfoDtoConverter addressInfoDtoConverter;

    @Override
    @Transactional
    public void createUserAddress(UserAddressCreateDto dto) {
        User user = userService.getById(dto.getUserId());
        Address address = addressService.save(addressCreateDtoConverter.convert(dto));

        UserAddress userAddress = UserAddress.builder()
                .address(address)
                .user(user)
                .build();
        userAddressService.save(userAddress);

    }

    @Override
    @Transactional
    public void createOrderAddress(OrderAddressCreateDto dto) {
        Order order = orderService.findById(dto.getOrderId());
        Address address = addressService.save(addressCreateDtoConverter.convert(dto));

        OrderAddress orderAddress = OrderAddress.builder()
                .address(address)
                .order(order)
                .comment(dto.getComment())
                .recipientPhone(dto.getRecipientPhone())
                .build();
        orderAddressService.save(orderAddress);
    }

    @Override
    public AddressInfoDto getById(Long id) {
        return addressInfoDtoConverter.convert(addressService.getById(id));
    }

    @Override
    public List<AddressInfoDto> getAll() {
        return addressInfoDtoConverter.convert(addressService.getAll());
    }
}
