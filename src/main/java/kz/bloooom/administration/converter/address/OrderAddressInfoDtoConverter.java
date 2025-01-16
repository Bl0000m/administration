package kz.bloooom.administration.converter.address;

import kz.bloooom.administration.domain.dto.address.OrderAddressInfoDto;
import kz.bloooom.administration.domain.entity.OrderAddress;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderAddressInfoDtoConverter {

    public OrderAddressInfoDto convert(OrderAddress source) {
        OrderAddressInfoDto target = new OrderAddressInfoDto();

        target.setId(source.getAddress().getId());
        target.setStreet(source.getAddress().getStreet());
        target.setBuilding(source.getAddress().getBuilding());
        target.setApartment(source.getAddress().getApartment());
        target.setEntrance(source.getAddress().getEntrance());
        target.setIntercom(source.getAddress().getIntercom());
        target.setFloor(source.getAddress().getFloor());
        target.setCity(source.getAddress().getCity());
        target.setPostalCode(source.getAddress().getPostalCode());
        target.setLatitude(source.getAddress().getLatitude());
        target.setLongitude(source.getAddress().getLongitude());
        target.setRecipientPhone(source.getRecipientPhone());
        target.setComment(source.getComment());
        return target;
    }
}
