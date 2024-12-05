package kz.bloooom.administration.service.impl;

import kz.bloooom.administration.domain.entity.BouquetPhoto;
import kz.bloooom.administration.repository.BouquetPhotoRepository;
import kz.bloooom.administration.service.BouquetPhotoService;
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
public class BouquetPhotoServiceImpl implements BouquetPhotoService {
    BouquetPhotoRepository bouquetPhotoRepository;

    @Override
    @Transactional
    public List<BouquetPhoto> saveAll(List<BouquetPhoto> photos) {
        return bouquetPhotoRepository.saveAll(photos);
    }
}
