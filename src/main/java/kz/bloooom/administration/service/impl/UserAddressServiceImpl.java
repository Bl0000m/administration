package kz.bloooom.administration.service.impl;

import kz.bloooom.administration.domain.entity.UserAddress;
import kz.bloooom.administration.repository.UserAddressRepository;
import kz.bloooom.administration.service.UserAddressService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserAddressServiceImpl implements UserAddressService {
    UserAddressRepository userAddressRepository;

    @Override
    @Transactional
    public UserAddress save(UserAddress address) {
        return userAddressRepository.save(address);
    }

    @Override
    public List<UserAddress> getAllUserAddressByUserId(Long userId) {
        return userAddressRepository.findAllByUserId(userId);
    }
}
