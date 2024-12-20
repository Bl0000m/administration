package kz.bloooom.administration.converter.stem_care;

import kz.bloooom.administration.domain.dto.stem_care.StemCareCreateDto;
import kz.bloooom.administration.domain.entity.StemCare;
import kz.bloooom.administration.util.JwtUtils;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class StemCareCreateDtoConverter {

    public StemCare convert(StemCareCreateDto dto) {
        return StemCare.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .createdDate(new Timestamp(System.currentTimeMillis()))
                .updatedDate(new Timestamp(System.currentTimeMillis()))
                .createdBy(JwtUtils.getKeycloakId())
                .updatedBy(JwtUtils.getKeycloakId())
                .build();
    }
}
