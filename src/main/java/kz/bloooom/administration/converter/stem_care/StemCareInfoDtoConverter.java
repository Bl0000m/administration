package kz.bloooom.administration.converter.stem_care;

import kz.bloooom.administration.domain.dto.stem_care.StemCareInfoDto;
import kz.bloooom.administration.domain.entity.StemCare;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StemCareInfoDtoConverter {

    public StemCareInfoDto convert(StemCare source) {
        return StemCareInfoDto.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .build();
    }

    public List<StemCareInfoDto> convert(List<StemCare> sources) {
        return CollectionUtils.isEmpty(sources) ?
                Collections.emptyList() :
                sources.stream().map(this::convert).collect(Collectors.toList());
    }
}