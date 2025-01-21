package kz.bloooom.administration.service;

import kz.bloooom.administration.domain.entity.UserAddress;

import java.util.List;

public interface UserAddressService {
    UserAddress save(UserAddress address);

    List<UserAddress> getAllUserAddressByUserId(Long userId);
}
