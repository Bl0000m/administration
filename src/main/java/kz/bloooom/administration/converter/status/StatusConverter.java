package kz.bloooom.administration.converter.status;


import kz.bloooom.administration.converter.AbstractEnumDtoConverter;
import kz.bloooom.administration.domain.entity.Status;
import kz.bloooom.administration.enumeration.status.StatusCode;
import org.springframework.stereotype.Component;

@Component
public class StatusConverter
        extends AbstractEnumDtoConverter<StatusCode, Status> {
}
