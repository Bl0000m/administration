package kz.bloooom.administration.service;

import kz.bloooom.administration.domain.entity.Status;
import kz.bloooom.administration.enumeration.status.StatusCode;

import java.util.List;

public interface StatusService {
    Status getById(Long id);

    List<Status> getAllStatuses();

    Status getByCode(StatusCode statusCode);
}
