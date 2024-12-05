package kz.bloooom.administration.converter.flower;

import kz.bloooom.administration.domain.dto.flower.FlowerCreatDto;
import kz.bloooom.administration.domain.entity.Flower;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlowerCreatDtoConverter {

    public Flower convert(FlowerCreatDto source) {
        Flower target = new Flower();
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        target.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
        return target;
    }
}
