package kz.bloooom.administration.facade;


import kz.bloooom.administration.domain.dto.AbstractEnumDto;
import kz.bloooom.administration.enumeration.status.StatusCode;

import java.util.List;

public interface StatusFacade {
    List<AbstractEnumDto<StatusCode>> getAllStatuses();

    AbstractEnumDto<StatusCode> getStatusById(Long id);

    AbstractEnumDto<StatusCode> getStatusByCode(StatusCode code);
}
