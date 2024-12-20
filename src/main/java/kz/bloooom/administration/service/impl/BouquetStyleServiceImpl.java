package kz.bloooom.administration.service.impl;

import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.domain.entity.BouquetStyle;
import kz.bloooom.administration.exception.BloomAdministrationException;
import kz.bloooom.administration.repository.BouquetStyleRepository;
import kz.bloooom.administration.service.BouquetStyleService;
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
public class BouquetStyleServiceImpl implements BouquetStyleService {
    BouquetStyleRepository bouquetStyleRepository;

    @Override
    @Transactional
    public BouquetStyle save(BouquetStyle bouquetStyle) {
        return bouquetStyleRepository.save(bouquetStyle);
    }

    @Override
    public BouquetStyle getById(Long id) {
        return bouquetStyleRepository.findById(id)
                .orElseThrow(() -> new BloomAdministrationException(
                        HttpStatus.NOT_FOUND,
                        ErrorCodeConstant.BOUQUET_STYLE_WITH_THIS_ID_DOEST_EXISTS,
                        "messages.exception.bouquet-style-not-found", id));
    }

    @Override
    public List<BouquetStyle> getAll() {
        return bouquetStyleRepository.findAll();
    }
}
