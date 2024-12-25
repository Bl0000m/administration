package kz.bloooom.administration.converter.additional_elements;

import kz.bloooom.administration.domain.dto.additional_elements.AdditionalElementsCreateDto;
import kz.bloooom.administration.domain.entity.AdditionalElements;
import kz.bloooom.administration.util.JwtUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdditionalElementsCreateDtoConverter {
    public AdditionalElements convert(AdditionalElementsCreateDto source) {
        AdditionalElements target = new AdditionalElements();
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setExample(source.getExample());
        target.setUnitOfMeasurement(source.getUnitOfMeasurement());
        target.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        target.setCreatedBy(JwtUtils.getKeycloakId());
        return target;
    }
}
