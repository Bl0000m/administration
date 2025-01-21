package kz.bloooom.administration.facade.impl;

import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.converter.flower_variety.FlowerVarietyCreateDtoConverter;
import kz.bloooom.administration.converter.flower_variety.FlowerVarietyInfoDtoConverter;
import kz.bloooom.administration.domain.dto.flower_variety.FlowerVarietyAddBranchDto;
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

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FlowerVarietyFacadeImpl implements FlowerVarietyFacade {
    FlowerVarietyService flowerVarietyService;
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
    public List<FlowerVarietyInfoDto> getAll() {
        return flowerVarietyInfoDtoConverter.convert(flowerVarietyService.getAll());
    }
}
