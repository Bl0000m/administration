package kz.bloooom.administration.service;

import kz.bloooom.administration.domain.entity.Address;

import java.util.List;

public interface AddressService {
    Address save(Address address);

    Address getById(Long id);

    List<Address> getAll();
}
