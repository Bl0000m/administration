package kz.bloooom.administration.facade.impl;

import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.converter.country.CountryInfoDtoConverter;
import kz.bloooom.administration.converter.flower.FlowerInfoDtoConverter;
import kz.bloooom.administration.converter.flower_variety.*;
import kz.bloooom.administration.converter.stem_care.StemCareInfoDtoConverter;
import kz.bloooom.administration.converter.temperature_care.TemperatureCareInfoDtoConverter;
import kz.bloooom.administration.converter.water_care.WaterCareInfoDtoConverter;
import kz.bloooom.administration.domain.dto.flower_variety.FlowerVarietyAddBranchDto;
import kz.bloooom.administration.domain.dto.flower_variety.FlowerVarietyBranchInfoDto;
import kz.bloooom.administration.domain.dto.flower_variety.FlowerVarietyCreateDto;
import kz.bloooom.administration.domain.dto.flower_variety.FlowerVarietyInfoDto;
import kz.bloooom.administration.domain.entity.BranchDivision;
import kz.bloooom.administration.domain.entity.Employee;
import kz.bloooom.administration.domain.entity.FlowerVariety;
import kz.bloooom.administration.domain.entity.FlowerVarietyPrice;
import kz.bloooom.administration.exception.BloomAdministrationException;
import kz.bloooom.administration.facade.FlowerVarietyFacade;
import kz.bloooom.administration.service.*;
import kz.bloooom.administration.util.JwtUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FlowerVarietyFacadeImpl implements FlowerVarietyFacade {
    FlowerVarietyService flowerVarietyService;
    FlowerInfoDtoConverter flowerInfoDtoConverter;
    SeasonInfoDtoConverter seasonInfoDtoConverter;
    StemCareInfoDtoConverter stemCareInfoDtoConverter;
    StemTypeInfoDtoConverter stemTypeInfoDtoConverter;
    TemperatureCareInfoDtoConverter temperatureCareInfoDtoConverter;
    WaterCareInfoDtoConverter waterCareInfoDtoConverter;
    CountryInfoDtoConverter countryInfoDtoConverter;
    FragranceInfoDtoConverter fragranceInfoDtoConverter;
    BranchDivisionService branchDivisionService;
    FlowerVarietyPriceService flowerVarietyPriceService;
    StorageService storageService;
    EmployeeService employeeService;
    FlowerVarietyCreateDtoConverter flowerVarietyCreateDtoConverter;
    FlowerVarietyInfoDtoConverter flowerVarietyInfoDtoConverter;

    @Override
    @Transactional
    public void create(FlowerVarietyCreateDto dto, MultipartFile photo) {
        FlowerVariety flowerVariety = flowerVarietyCreateDtoConverter.convert(dto);
        addPhoto(flowerVariety, photo);
        flowerVariety = flowerVarietyService.save(flowerVariety);

        FlowerVarietyPrice flowerVarietyPrice = createPrice(dto, flowerVariety);
        flowerVarietyPriceService.create(flowerVarietyPrice);
    }

    @Override
    @Transactional
    public void addFlowerVarietyToBranch(FlowerVarietyAddBranchDto dto) {
        FlowerVarietyPrice flowerVarietyPrice = createFlowerVarietyPrice(dto);
        flowerVarietyPriceService.create(flowerVarietyPrice);
    }

    private FlowerVarietyPrice createFlowerVarietyPrice(FlowerVarietyAddBranchDto dto) {
        FlowerVariety flowerVariety = flowerVarietyService.getById(dto.getFlowerVarietyId());
        BranchDivision branchDivision = branchDivisionService.getById(dto.getBranchDivisionId());

        return FlowerVarietyPrice
                .builder()
                .flowerVariety(flowerVariety)
                .branchDivision(branchDivision)
                .price(dto.getPrice())
                .currency(dto.getCurrency())
                .validFrom(dto.getValidFrom().atStartOfDay())
                .validTo(dto.getValidTo().atStartOfDay())
                .createdDate(new Timestamp(System.currentTimeMillis()))
                .updatedDate(new Timestamp(System.currentTimeMillis()))
                .createdBy(JwtUtils.getKeycloakId())
                .updatedBy(JwtUtils.getKeycloakId())
                .build();

    }

    @Override
    @Transactional
    public void addFlowerVarietyPrice(FlowerVarietyAddBranchDto dto) {
        boolean isOverlap = flowerVarietyPriceService.existsByDateOverlap(
                dto.getFlowerVarietyId(),
                dto.getBranchDivisionId(),
                dto.getValidFrom().atStartOfDay(),
                dto.getValidTo().atStartOfDay());

        if (isOverlap) {
            throw new BloomAdministrationException(
                    HttpStatus.NOT_FOUND,
                    ErrorCodeConstant.PRICE_IN_THIS_RANGE_EXITS_DOEST_EXISTS,
                    "messages.exception.price-for-date-exist", dto.getValidFrom(), dto.getValidTo());
        }
        FlowerVarietyPrice flowerVarietyPrice = createFlowerVarietyPrice(dto);

        flowerVarietyPriceService.create(flowerVarietyPrice);

    }

    private FlowerVarietyPrice createPrice(FlowerVarietyCreateDto dto,
                                           FlowerVariety flowerVariety) {
        BranchDivision branchDivision = Objects.nonNull(dto.getBranchDivisionId()) ?
                branchDivisionService.getById(dto.getBranchDivisionId()) : null;
        Employee employee = employeeService.getById(dto.getEmployeeId());

        return FlowerVarietyPrice
                .builder()
                .flowerVariety(flowerVariety)
                .branchDivision(branchDivision)
                .employee(employee)
                .price(dto.getPrice())
                .currency(dto.getCurrency())
                .validFrom(dto.getValidFrom().atStartOfDay())
                .validTo(dto.getValidTo().atStartOfDay())
                .createdDate(new Timestamp(System.currentTimeMillis()))
                .updatedDate(new Timestamp(System.currentTimeMillis()))
                .createdBy(JwtUtils.getKeycloakId())
                .updatedBy(JwtUtils.getKeycloakId())
                .build();
    }

    private void addPhoto(FlowerVariety flowerVariety, MultipartFile photo) {
        if (Objects.nonNull(photo) && !photo.isEmpty()) {
            if (StringUtils.isNotBlank(flowerVariety.getImage())) {
                storageService.delete(flowerVariety.getImage());
            }
            flowerVariety.setImage(storageService.uploadFile(photo));
        }
    }

    @Override
    public FlowerVarietyInfoDto getById(Long id) {
        return flowerVarietyInfoDtoConverter.convert(flowerVarietyService.getById(id));
    }

    @Override
    public List<FlowerVarietyBranchInfoDto> getAllByBranchId(Long branchId) {
        List<FlowerVarietyPrice> flowerVarietyPrices = flowerVarietyPriceService.getAllByBranchId(branchId);

        return flowerVarietyPrices.stream()
                .map(this::mapToFlowerVarietyBranchInfoDto)
                .collect(Collectors.toList());
    }

    private FlowerVarietyBranchInfoDto mapToFlowerVarietyBranchInfoDto(FlowerVarietyPrice source) {
        FlowerVarietyBranchInfoDto target = new FlowerVarietyBranchInfoDto();

        if (source.getFlowerVariety() != null) {
            target.setId(source.getFlowerVariety().getId());
            target.setName(source.getFlowerVariety().getName());
            target.setFlowerInfo(flowerInfoDtoConverter.convert(source.getFlowerVariety().getFlower()));
            target.setShelfLifeDaysMin(source.getFlowerVariety().getShelfLifeDaysMin());
            target.setShelfLifeDaysMax(source.getFlowerVariety().getShelfLifeDaysMax());
            target.setFragranceInfo(fragranceInfoDtoConverter.convert(source.getFlowerVariety().getFragrance()));
            target.setSeasonInfo(seasonInfoDtoConverter.convert(source.getFlowerVariety().getSeason()));
            target.setSteamTypeInfo(stemTypeInfoDtoConverter.convert(source.getFlowerVariety().getSteamType()));
            target.setColor(source.getFlowerVariety().getColor());
            target.setBudSizeMin(source.getFlowerVariety().getBudSizeMin());
            target.setBudSizeMax(source.getFlowerVariety().getBudSizeMax());
            target.setStemHeightSizeMin(source.getFlowerVariety().getStemHeightSizeMin());
            target.setStemHeightSizeMax(source.getFlowerVariety().getStemHeightSizeMax());
            target.setImage(source.getFlowerVariety().getImage());
            target.setStemCareInfo(stemCareInfoDtoConverter.convert(source.getFlowerVariety().getStemCare()));
            target.setTemperatureCareInfo(temperatureCareInfoDtoConverter.convert(source.getFlowerVariety().getTemperatureCare()));
            target.setWaterCareInfo(waterCareInfoDtoConverter.convert(source.getFlowerVariety().getWaterCare()));
            target.setCountryInfoDto(countryInfoDtoConverter.convert(source.getFlowerVariety().getCountry()));
        }

        target.setPrice(source.getPrice());
        target.setCurrency(source.getCurrency() != null ? source.getCurrency().getTitle() : null);
        target.setValidFrom(source.getValidFrom());
        target.setValidTo(source.getValidTo());

        return target;
    }

    @Override
    public List<FlowerVarietyInfoDto> getAll() {
        return flowerVarietyInfoDtoConverter.convert(flowerVarietyService.getAll());
    }
}
