package kz.bloooom.administration.converter.address;

import kz.bloooom.administration.domain.dto.address.AddressCreateDto;
import kz.bloooom.administration.domain.dto.address.UserAddressCreateDto;
import kz.bloooom.administration.domain.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressCreateDtoConverter {

    public Address convert(AddressCreateDto source) {
        Address target = new Address();
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
}
