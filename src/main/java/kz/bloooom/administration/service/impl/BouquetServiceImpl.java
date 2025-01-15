package kz.bloooom.administration.service.impl;

import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.domain.entity.Bouquet;
import kz.bloooom.administration.exception.BloomAdministrationException;
import kz.bloooom.administration.repository.BouquetRepository;
import kz.bloooom.administration.service.BouquetService;
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
public class BouquetServiceImpl implements BouquetService {

    BouquetRepository bouquetRepository;

    @Override
    @Transactional
    public Bouquet create(Bouquet bouquet) {
        return bouquetRepository.save(bouquet);
    }

    @Override
    public Bouquet getById(Long id) {
        return bouquetRepository.findById(id)
                .orElseThrow(() -> new BloomAdministrationException(
                        HttpStatus.NOT_FOUND,
                        ErrorCodeConstant.BOUQUET_WITH_THIS_ID_DOEST_EXISTS,
                        "messages.exception.bouquet-not-found", id));
    }

    @Override
    public List<Bouquet> getAll() {
        return bouquetRepository.findAll();
    }

    @Override
    public List<Bouquet> getAllBouquetsByEmployeeId(Long employeeId) {
        return bouquetRepository.findAllByEmployeeId(employeeId);
    }
}
