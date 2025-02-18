package kz.bloooom.administration.facade.impl;

import kz.bloooom.administration.converter.additional_elements.AdditionalElementsNameInfoDtoConverter;
import kz.bloooom.administration.converter.bouquet.BouquetCreateDtoConverter;
import kz.bloooom.administration.converter.bouquet.BouquetDetailInfoDtoConverter;
import kz.bloooom.administration.converter.bouquet.BouquetInfoDtoConverter;
import kz.bloooom.administration.converter.flower_variety.FlowerVarietyNameInfoDtoConverter;
import kz.bloooom.administration.converter.img.ImageInfoConverter;
import kz.bloooom.administration.domain.dto.additional_elements.AdditionalElementsShortInfoDto;
import kz.bloooom.administration.domain.dto.bouquet.*;
import kz.bloooom.administration.domain.dto.flower_variety.FlowerVarietyShortInfoToAttachBouquetDto;
import kz.bloooom.administration.domain.dto.storage.FileInfo;
import kz.bloooom.administration.domain.entity.*;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
    AdditionalElementsService additionalElementsService;
    ImageInfoConverter imageInfoConverter;
    BouquetAdditionalElementsService bouquetAdditionalElementsService;
    FlowerVarietyNameInfoDtoConverter flowerVarietyNameInfoDtoConverter;
    AdditionalElementsNameInfoDtoConverter additionalElementsNameInfoDtoConverter;
    BouquetFlowersService bouquetFlowersService;
    BouquetCreateDtoConverter bouquetCreateDtoConverter;
    BouquetInfoDtoConverter bouquetInfoDtoConverter;
    BouquetDetailInfoDtoConverter bouquetDetailInfoDtoConverter;
    BouquetBranchPriceService bouquetBranchPriceService;
    BranchDivisionService branchDivisionService;
    EmployeeService employeeService;

    @Override
    @Transactional
    public void createBouquet(
            BouquetCreateDto bouquetCreateDto,
            List<MultipartFile> files) {

        Bouquet bouquet = bouquetCreateDtoConverter.convert(bouquetCreateDto);
        bouquet = bouquetService.create(bouquet);
        log.info("Created bouquet: {}", bouquet.getId());
        String organizationPath = getOrganizationPath(bouquet.getName(), bouquet.getId());
        savePhoto(files, bouquet, organizationPath);

        attachFlowerVarietiesToBouquet(bouquetCreateDto, bouquet);

        attachAdditionalElementsToBouquet(bouquetCreateDto, bouquet);

        // create price for bouquet
        createBouquetBranchPrice(bouquetCreateDto, bouquet);
    }

    @Override
    @Transactional
    public void addBranchToBouquet(BouquetAddBranchDto bouquetAddBranchDto) {
        BranchDivision branchDivision = branchDivisionService.getById(bouquetAddBranchDto.getBranchDivisionId());

        Bouquet bouquet = bouquetService.getById(bouquetAddBranchDto.getBouquetId());

        BouquetBranchPrice bouquetBranchPrice = BouquetBranchPrice
                .builder()
                .bouquet(bouquet)
                .branchDivision(branchDivision)
                .price(bouquetAddBranchDto.getPrice())
                .build();

        bouquetBranchPriceService.create(bouquetBranchPrice);
    }

    private void createBouquetBranchPrice(
            BouquetCreateDto dto,
            Bouquet bouquet) {
        BranchDivision branchDivision = Objects.nonNull(dto.getBranchId()) ?
                                        branchDivisionService.getById(dto.getBranchId()) : null;
        Employee employee = employeeService.getById(dto.getEmployeeId());

        BouquetBranchPrice bouquetBranchPrice =
                BouquetBranchPrice
                        .builder()
                        .bouquet(bouquet)
                        .employee(employee)
                        .price(dto.getPrice())
                        .branchDivision(branchDivision)
                        .build();

        bouquetBranchPriceService.create(bouquetBranchPrice);
    }

    @Override
    public BouquetDetailInfoDto getById(Long id) {
        return bouquetDetailInfoDtoConverter.convert(bouquetService.getById(id));
    }

    @Override
    public List<BouquetBranchInfoDto> getAllByBranchId(Long branchId) {
        List<BouquetBranchPrice> bouquetBranchPrices = bouquetBranchPriceService.getAllBouquetBranchByBranchId(
                branchId);
        return bouquetBranchPrices.stream()
                                  .map(this::mapToBouquetBranchInfoDto)
                                  .collect(Collectors.toList());
    }

    private BouquetBranchInfoDto mapToBouquetBranchInfoDto(BouquetBranchPrice source) {
        BouquetBranchInfoDto target = new BouquetBranchInfoDto();
        if (source.getBouquet() != null) {
            target.setId(source.getBouquet().getId());
            target.setName(source.getBouquet().getName());
            target.setAuthor(source.getBouquet().getEmployee().getName());
            target.setBouquetPhotos(imageInfoConverter.convert(source.getBouquet().getBouquetPhotos()));
            target.setPrice(source.getPrice());
            target.setBouquetStyle(source.getBouquet().getBouquetStyle().getName());
            target.setFlowerVarietyInfo(flowerVarietyNameInfoDtoConverter.convert(source.getBouquet().getFlowers(),
                                                                                  source.getBouquet().getId()));
            target.setAdditionalElements(
                    additionalElementsNameInfoDtoConverter.convert(source.getBouquet().getAdditionalElements(),
                                                                   source.getBouquet().getId()));
        }
        return target;
    }

    @Override
    public List<BouquetInfoDto> getAll() {
        List<Bouquet> bouquets = bouquetService.getAll();
        return bouquetInfoDtoConverter.convert(bouquets);
    }

    private void attachFlowerVarietiesToBouquet(
            BouquetCreateDto bouquetCreateDto,
            Bouquet bouquet) {
        Map<Long, Integer> flowerQuantityMap = bouquetCreateDto.getFlowersVarietyInfo()
                                                               .stream()
                                                               .collect(Collectors.toMap(
                                                                       FlowerVarietyShortInfoToAttachBouquetDto::getId,
                                                                       FlowerVarietyShortInfoToAttachBouquetDto::getQuantity
                                                                                        ));

        List<Long> flowerVarietyIds = new ArrayList<>(flowerQuantityMap.keySet());

        List<FlowerVariety> flowerVarieties =
                flowerVarietyService.getFlowerVarietiesByIdIn(flowerVarietyIds);

        List<BouquetFlowerVariety> bouquetFlowerVarietyList = flowerVarieties.stream()
                                                                             .map(flowerVariety -> BouquetFlowerVariety.builder()
                                                                                                                       .bouquet(
                                                                                                                               bouquet)
                                                                                                                       .flowerVariety(
                                                                                                                               flowerVariety)
                                                                                                                       .quantity(
                                                                                                                               flowerQuantityMap.get(
                                                                                                                                       flowerVariety.getId()))
                                                                                                                       .build())
                                                                             .collect(Collectors.toList());

        bouquetFlowersService.saveAll(bouquetFlowerVarietyList);
    }

    private void attachAdditionalElementsToBouquet(
            BouquetCreateDto bouquetCreateDto,
            Bouquet bouquet) {
        Map<Long, AdditionalElementsShortInfoDto> additionalElementsMap = bouquetCreateDto.getAdditionalElements()
                                                                                          .stream()
                                                                                          .collect(Collectors.toMap(
                                                                                                  AdditionalElementsShortInfoDto::getId,
                                                                                                  additionalElement -> additionalElement
                                                                                                                   ));


        List<Long> additionalElementsIds = new ArrayList<>(additionalElementsMap.keySet());

        List<AdditionalElements> additionalElements =
                additionalElementsService.getAdditionalElementsByIdIn(additionalElementsIds);

        List<BouquetAdditionalElements> bouquetAdditionalElementsList = additionalElements.stream()
                                                                                          .map(elements -> BouquetAdditionalElements.builder()
                                                                                                                                    .bouquet(
                                                                                                                                            bouquet)
                                                                                                                                    .additionalElements(
                                                                                                                                            elements)
                                                                                                                                    .quantity(
                                                                                                                                            additionalElementsMap.get(
                                                                                                                                                                         elements.getId())
                                                                                                                                                                 .getQuantity())
                                                                                                                                    .color(additionalElementsMap.get(
                                                                                                                                                                        elements.getId())
                                                                                                                                                                .getColor())
                                                                                                                                    .build())
                                                                                          .collect(Collectors.toList());

        bouquetAdditionalElementsService.saveAll(bouquetAdditionalElementsList);
    }

    private String getOrganizationPath(
            String companyName,
            Long bouquetId) {
        return companyName + File.separator
                + bouquetId + File.separator;
    }

    private void savePhoto(
            List<MultipartFile> files,
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

    @Override
    @Transactional
    public void deletePrice(final BouquetDeletePriceDto dto) {
        BouquetBranchPrice bouquetBranchPrice = bouquetBranchPriceService.getBouquetBranchByBouquetIdAndBranchId(
                dto.getBouquetId(), dto.getBranchDivisionId());

        bouquetBranchPrice.setPrice(null);
        bouquetBranchPriceService.create(bouquetBranchPrice);
    }
}
