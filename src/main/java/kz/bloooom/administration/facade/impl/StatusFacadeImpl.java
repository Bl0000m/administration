package kz.bloooom.administration.facade.impl;

import kz.bloooom.administration.converter.status.StatusConverter;
import kz.bloooom.administration.domain.dto.AbstractEnumDto;
import kz.bloooom.administration.enumeration.status.StatusCode;
import kz.bloooom.administration.facade.StatusFacade;
import kz.bloooom.administration.service.StatusService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StatusFacadeImpl implements StatusFacade {
    StatusService statusService;
    StatusConverter statusConverter;

    @Override
    public List<AbstractEnumDto<StatusCode>> getAllStatuses() {
        return statusConverter.convert(statusService.getAllStatuses());
    }

    @Override
    public AbstractEnumDto<StatusCode> getStatusById(Long id) {
        return statusConverter.convert(statusService.getById(id));
    }

    @Override
    public AbstractEnumDto<StatusCode> getStatusByCode(StatusCode code) {
        return statusConverter.convert(statusService.getByCode(code));
    }
}

