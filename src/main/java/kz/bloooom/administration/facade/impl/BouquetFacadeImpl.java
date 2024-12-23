package kz.bloooom.administration.facade.impl;

import kz.bloooom.administration.converter.bouquet.BouquetCreateDtoConverter;
import kz.bloooom.administration.converter.bouquet.BouquetInfoDtoConverter;
import kz.bloooom.administration.domain.dto.bouquet.BouquetCreateDto;
import kz.bloooom.administration.domain.dto.bouquet.BouquetInfoDto;
import kz.bloooom.administration.domain.dto.flower_variety.FlowerVarietyShortInfoToAttachBouquetDto;
import kz.bloooom.administration.domain.dto.storage.FileInfo;
import kz.bloooom.administration.domain.entity.Bouquet;
import kz.bloooom.administration.domain.entity.BouquetFlowerVariety;
import kz.bloooom.administration.domain.entity.BouquetPhoto;
import kz.bloooom.administration.domain.entity.FlowerVariety;
import kz.bloooom.administration.facade.BouquetFacade;
import kz.bloooom.administration.service.*;
import kz.bloooom.administration.util.JwtUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BouquetFacadeImpl implements BouquetFacade {

    BouquetService bouquetService;
    StorageService storageService;
    BouquetPhotoService photoService;
    FlowerVarietyService flowerVarietyService;
    BouquetFlowersService bouquetFlowersService;
    BouquetCreateDtoConverter bouquetCreateDtoConverter;
    BouquetInfoDtoConverter bouquetInfoDtoConverter;

    @Override
    @Transactional
    public void createBouquet(BouquetCreateDto bouquetCreateDto,
                              List<MultipartFile> files) {

        Bouquet bouquet = bouquetCreateDtoConverter.convert(bouquetCreateDto);
        bouquet = bouquetService.create(bouquet);
        log.info("Created bouquet: {}", bouquet.getId());
        String organizationPath = getOrganizationPath(bouquet.getCompany().getName(), bouquet.getId());
        savePhoto(files, bouquet, organizationPath);

        attachFlowerVarietiesToBouquet(bouquetCreateDto, bouquet);
    }

    @Override
    public List<BouquetInfoDto> getAll() {
        List<Bouquet> bouquets = bouquetService.getAll();
        return bouquetInfoDtoConverter.convert(bouquets);
    }

    private void attachFlowerVarietiesToBouquet(BouquetCreateDto bouquetCreateDto, Bouquet bouquet) {
        Map<Long, Integer> flowerQuantityMap = bouquetCreateDto.getFlowersVarietyInfo()
                .stream()
                .collect(Collectors.toMap(FlowerVarietyShortInfoToAttachBouquetDto::getId,
                        FlowerVarietyShortInfoToAttachBouquetDto::getQuantity));

        List<FlowerVariety> flowerVarieties =
                flowerVarietyService.getFlowerVarietiesByIdIn((List<Long>) flowerQuantityMap.keySet());

        List<BouquetFlowerVariety> bouquetFlowerVarietyList = flowerVarieties.stream()
                .map(flowerVariety -> BouquetFlowerVariety.builder()
                        .bouquet(bouquet)
                        .flowerVariety(flowerVariety)
                        .quantity(flowerQuantityMap.get(flowerVariety.getId()))
                        .build())
                .collect(Collectors.toList());

        bouquetFlowersService.saveAll(bouquetFlowerVarietyList);
    }

    private String getOrganizationPath(String companyName, Long bouquetId) {
        return companyName + File.separator
                + bouquetId + File.separator;
    }

    private void savePhoto(List<MultipartFile> files,
                           Bouquet bouquet,
                           String organizationUrl) {
        List<BouquetPhoto> photos = new ArrayList<>();
        String keycloakId = JwtUtils.getKeycloakId();
        for (MultipartFile file : files) {
            FileInfo info = storageService.storeFile(file, organizationUrl);
            BouquetPhoto photo = new BouquetPhoto();
            photo.setDirectory(organizationUrl);
            photo.setTitle(info.getName());
            photo.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            photo.setBouquet(bouquet);
            photo.setUploadedBy(keycloakId);
            photos.add(photo);
        }
        photoService.saveAll(photos);
        log.info("Stored bouquet photos for bouquet: {}", bouquet.getId());
    }


}
