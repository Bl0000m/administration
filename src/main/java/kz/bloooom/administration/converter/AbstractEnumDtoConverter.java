package kz.bloooom.administration.converter;


import kz.bloooom.administration.domain.dto.AbstractEnumDto;
import kz.bloooom.administration.domain.entity.AbstractEnumEntity;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AbstractEnumDtoConverter<
        E extends Enum<E>,
        S extends AbstractEnumEntity<E>> {


    public AbstractEnumDto<E> convert(S source) {
        AbstractEnumDto<E> target = new AbstractEnumDto<>();
        target.setId(source.getId());
        target.setCode(source.getCode());
        target.setName(source.getName().get("ru"));
        target.setDescription(source.getDescription().get("ru"));
        return target;
    }

    public List<AbstractEnumDto<E>> convert(List<S> source) {
        return CollectionUtils.isEmpty(source) ?
                Collections.emptyList() :
                source.stream()
                        .map(this::convert)
                        .collect(Collectors.toList());
    }
}
