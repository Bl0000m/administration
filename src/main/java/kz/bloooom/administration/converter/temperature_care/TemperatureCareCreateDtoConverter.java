package kz.bloooom.administration.converter.temperature_care;

import kz.bloooom.administration.domain.dto.temperature_care.TemperatureCareCreateDto;
import kz.bloooom.administration.domain.entity.TemperatureCare;
import kz.bloooom.administration.util.JwtUtils;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class TemperatureCareCreateDtoConverter {
    public TemperatureCare convert(TemperatureCareCreateDto dto) {
        return TemperatureCare.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .createdDate(new Timestamp(System.currentTimeMillis()))
                .updatedDate(new Timestamp(System.currentTimeMillis()))
                .createdBy(JwtUtils.getKeycloakId())
                .updatedBy(JwtUtils.getKeycloakId())
                .build();
    }
}
