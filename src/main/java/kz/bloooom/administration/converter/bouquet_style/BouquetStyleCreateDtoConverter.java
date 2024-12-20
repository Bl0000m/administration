package kz.bloooom.administration.converter.bouquet_style;

import kz.bloooom.administration.domain.dto.bouquet_style.BouquetStyleCreateDto;
import kz.bloooom.administration.domain.entity.BouquetStyle;
import kz.bloooom.administration.util.JwtUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BouquetStyleCreateDtoConverter {

    public BouquetStyle convert(BouquetStyleCreateDto dto) {
        return BouquetStyle.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .createdDate(new Timestamp(System.currentTimeMillis()))
                .updatedDate(new Timestamp(System.currentTimeMillis()))
                .createdBy(JwtUtils.getKeycloakId())
                .updatedBy(JwtUtils.getKeycloakId())
                .isVerify(false)
                .build();
    }
}
