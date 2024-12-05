package kz.bloooom.administration.facade.impl;

import kz.bloooom.administration.converter.flower.FlowerCreatDtoConverter;
import kz.bloooom.administration.domain.dto.flower.FlowerCreatDto;
import kz.bloooom.administration.domain.entity.Flower;
import kz.bloooom.administration.facade.FlowerFacade;
import kz.bloooom.administration.service.FlowerService;
import kz.bloooom.administration.service.StorageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FlowerFacadeImpl implements FlowerFacade {

    FlowerCreatDtoConverter flowerCreatDtoConverter;
    FlowerService flowerService;
    StorageService storageService;

    @Override
    @Transactional
    public void createFlower(FlowerCreatDto flowerCreatDto, MultipartFile photo) {
        Flower flower = flowerCreatDtoConverter.convert(flowerCreatDto);
        addPhoto(flower, photo);
        flowerService.create(flower);
        log.info("Flower created: {}", flower);
    }

    private void addPhoto(Flower flower, MultipartFile photo) {
        if (Objects.nonNull(photo) && !photo.isEmpty()) {
            if (StringUtils.isNotBlank(flower.getPhoto())) {
                storageService.delete(flower.getPhoto());
            }
            flower.setPhoto(storageService.uploadFile(photo));
        }
    }
}
