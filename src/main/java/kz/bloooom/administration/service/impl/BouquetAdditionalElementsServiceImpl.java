package kz.bloooom.administration.service.impl;

import kz.bloooom.administration.domain.entity.BouquetAdditionalElements;
import kz.bloooom.administration.repository.BouquetAdditionalElementsRepository;
import kz.bloooom.administration.service.BouquetAdditionalElementsService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BouquetAdditionalElementsServiceImpl implements BouquetAdditionalElementsService {

    BouquetAdditionalElementsRepository bouquetAdditionalElementsRepository;

    @Override
    @Transactional
    public void saveAll(List<BouquetAdditionalElements> bouquetAdditionalElementsList) {
        bouquetAdditionalElementsRepository.saveAll(bouquetAdditionalElementsList);
    }

    @Override
    public BouquetAdditionalElements getByAdditionalElementIdAndBouquetId(Long additionalElementId, Long bouquetId) {
        return bouquetAdditionalElementsRepository.findByAdditionalElementsIdAndBouquetId(additionalElementId, bouquetId);
    }
}
