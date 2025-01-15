package kz.bloooom.administration.converter.bouquet_style;

import kz.bloooom.administration.domain.dto.bouquet_style.BouquetStyleInfoDto;
import kz.bloooom.administration.domain.entity.BouquetStyle;
import kz.bloooom.administration.service.EmployeeService;
import kz.bloooom.administration.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BouquetStyleInfoDtoConverter {
    EmployeeService employeeService;
    UserService userService;

    public BouquetStyleInfoDto convert(BouquetStyle source) {
        return BouquetStyleInfoDto.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
//                .createdBy(Objects.nonNull(employeeService.findByKeycloakId(source.getCreatedBy())) ?
//                        employeeService.findByKeycloakId(source.getCreatedBy()).getName() :
//                        userService.getByKeycloakId(source.getCreatedBy()).getName())
                .isVerify(source.isVerify())
                .build();
    }

    public List<BouquetStyleInfoDto> convert(List<BouquetStyle> sources) {
        return CollectionUtils.isEmpty(sources) ?
                Collections.emptyList() :
                sources.stream().map(this::convert).collect(Collectors.toList());
    }
}
