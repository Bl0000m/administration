package kz.bloooom.administration.converter.water_care;

import kz.bloooom.administration.domain.dto.water_care.WaterCareCreateDto;
import kz.bloooom.administration.domain.entity.WaterCare;
import kz.bloooom.administration.util.JwtUtils;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class WaterCareCreateDtoConverter {
    public WaterCare convert(WaterCareCreateDto dto) {
        return WaterCare.builder()
                .name(dto.getName())
                .createdDate(new Timestamp(System.currentTimeMillis()))
                .updatedDate(new Timestamp(System.currentTimeMillis()))
                .createdBy(JwtUtils.getKeycloakId())
                .updatedBy(JwtUtils.getKeycloakId())
                .build();
    }
}
