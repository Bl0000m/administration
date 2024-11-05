package kz.bloooom.administration.service.impl;

import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.domain.entity.Status;
import kz.bloooom.administration.enumeration.status.StatusCode;
import kz.bloooom.administration.exception.BloomAdministrationException;
import kz.bloooom.administration.repository.StatusRepository;
import kz.bloooom.administration.service.StatusService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StatusServiceImpl implements StatusService {

    StatusRepository statusRepository;

    @Override
    public Status getById(Long id) {
        return statusRepository.findById(id)
                .orElseThrow(() -> new BloomAdministrationException(
                        HttpStatus.NOT_FOUND,
                        ErrorCodeConstant.STATUS_WITH_THIS_ID_DOEST_EXISTS,
                        "messages.exception.status-not-found", id));
    }

    @Override
    public List<Status> getAllStatuses() {
        return statusRepository.findAll();
    }

    @Override
    public Status getByCode(StatusCode statusCode) {
        return statusRepository.getByCode(statusCode)
                .orElseThrow(() -> new BloomAdministrationException(
                        HttpStatus.NOT_FOUND,
                        ErrorCodeConstant.STATUS_WITH_THIS_CODE_DOEST_EXISTS,
                        "messages.exception.status-code-not-found", statusCode));
    }
}
