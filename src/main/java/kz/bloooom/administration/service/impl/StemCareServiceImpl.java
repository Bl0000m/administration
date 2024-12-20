package kz.bloooom.administration.service.impl;

import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.domain.entity.StemCare;
import kz.bloooom.administration.exception.BloomAdministrationException;
import kz.bloooom.administration.repository.StemCareRepository;
import kz.bloooom.administration.service.StemCareService;
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
public class StemCareServiceImpl implements StemCareService {
    StemCareRepository stemCareRepository;

    @Override
    public StemCare save(StemCare stemCare) {
        return stemCareRepository.save(stemCare);
    }

    @Override
    public StemCare getById(Long id) {
        return stemCareRepository.findById(id)
                .orElseThrow(() -> new BloomAdministrationException(
                        HttpStatus.NOT_FOUND,
                        ErrorCodeConstant.STEM_CARE_WITH_THIS_ID_DOEST_EXISTS,
                        "messages.exception.stem-care-not-found", id));
    }

    @Override
    public List<StemCare> getAll() {
        return stemCareRepository.findAll();
    }
}
