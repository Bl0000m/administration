package kz.bloooom.administration.facade.impl;

import kz.bloooom.administration.converter.bouquet.BouquetCreateDtoConverter;
import kz.bloooom.administration.converter.bouquet.BouquetInfoDtoConverter;
import kz.bloooom.administration.domain.dto.bouquet.BouquetCreateDto;
import kz.bloooom.administration.domain.dto.bouquet.BouquetInfoDto;
import kz.bloooom.administration.domain.dto.flower.FlowerShortInfoToAttachBouquetDto;
import kz.bloooom.administration.domain.dto.storage.FileInfo;
import kz.bloooom.administration.domain.entity.Bouquet;
import kz.bloooom.administration.domain.entity.BouquetFlowers;
import kz.bloooom.administration.domain.entity.BouquetPhoto;
import kz.bloooom.administration.domain.entity.Flower;
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
    FlowerService flowerService;
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

    }

    @Override
    public List<BouquetInfoDto> getAll() {
        List<Bouquet> bouquets = bouquetService.getAll();
        return bouquetInfoDtoConverter.convert(bouquets);
    }

    private void attachFlowersToBouquet(BouquetCreateDto bouquetCreateDto, Bouquet bouquet) {
        Map<Long, Integer> flowerQuantityMap = bouquetCreateDto.getFlowersInfo()
                .stream()
                .collect(Collectors.toMap(FlowerShortInfoToAttachBouquetDto::getId, FlowerShortInfoToAttachBouquetDto::getQuantity));

        List<Flower> flowers = flowerService.getFlowersByIdIn((List<Long>) flowerQuantityMap.keySet());

        List<BouquetFlowers> bouquetFlowersList = flowers.stream()
                .map(flower -> BouquetFlowers.builder()
                        .bouquet(bouquet)
                        .flower(flower)
                        .quantity(flowerQuantityMap.get(flower.getId()))
                        .build())
                .collect(Collectors.toList());

        bouquetFlowersService.saveAll(bouquetFlowersList);
    }

    private String getOrganizationPath(String companyName, Long questionId) {
        return companyName + File.separator
                + questionId + File.separator;
    }

    private void savePhoto(List<MultipartFile> files,
                           Bouquet bouquet,
                           String organizationUrl) {
        List<BouquetPhoto> photos = new ArrayList<>();
        String keycloakId = JwtUtils.getKeycloakId();
        LocalDateTime now = LocalDateTime.now();
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
