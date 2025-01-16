package kz.bloooom.administration.converter.address;

import kz.bloooom.administration.domain.dto.address.AddressInfoDto;
import kz.bloooom.administration.domain.entity.Address;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddressInfoDtoConverter {

    public AddressInfoDto convert(Address source) {
        AddressInfoDto target = new AddressInfoDto();
        target.setId(source.getId());
        target.setStreet(source.getStreet());
        target.setBuilding(source.getBuilding());
        target.setApartment(source.getApartment());
        target.setEntrance(source.getEntrance());
        target.setIntercom(source.getIntercom());
        target.setFloor(source.getFloor());
        target.setCity(source.getCity());
        target.setPostalCode(source.getPostalCode());
        target.setLatitude(source.getLatitude());
        target.setLongitude(source.getLongitude());
        return target;
    }

    public List<AddressInfoDto> convert(List<Address> addresses) {
        return CollectionUtils.isEmpty(addresses) ?
                Collections.emptyList() :
                addresses.stream().map(this::convert).collect(Collectors.toList());
    }
}
