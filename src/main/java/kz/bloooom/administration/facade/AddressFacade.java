package kz.bloooom.administration.facade;

import kz.bloooom.administration.domain.dto.address.OrderAddressCreateDto;
import kz.bloooom.administration.domain.dto.address.UserAddressCreateDto;
import kz.bloooom.administration.domain.dto.address.AddressInfoDto;

import java.util.List;

public interface AddressFacade {
    void createUserAddress(UserAddressCreateDto dto);

    void createOrderAddress(OrderAddressCreateDto dto);

    AddressInfoDto getById(Long id);

    List<AddressInfoDto> getAll();
}
