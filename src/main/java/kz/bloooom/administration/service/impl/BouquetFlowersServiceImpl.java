package kz.bloooom.administration.service.impl;

import kz.bloooom.administration.domain.entity.BouquetFlowerVariety;
import kz.bloooom.administration.repository.BouquetFlowersRepository;
import kz.bloooom.administration.service.BouquetFlowersService;
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
public class BouquetFlowersServiceImpl implements BouquetFlowersService {
    BouquetFlowersRepository bouquetFlowersRepository;

    @Override
    @Transactional
    public void saveAll(List<BouquetFlowerVariety> bouquetFlowers) {
        bouquetFlowersRepository.saveAll(bouquetFlowers);
    }
}
