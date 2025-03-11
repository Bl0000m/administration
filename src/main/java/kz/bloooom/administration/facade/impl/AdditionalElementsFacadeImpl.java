package kz.bloooom.administration.facade.impl;

import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.converter.additional_elements.AdditionalElementPriceInfoDtoConverter;
import kz.bloooom.administration.converter.additional_elements.AdditionalElementsCreateDtoConverter;
import kz.bloooom.administration.converter.additional_elements.AdditionalElementsInfoDtoConverter;
import kz.bloooom.administration.domain.dto.additional_elements.*;
import kz.bloooom.administration.domain.entity.*;
import kz.bloooom.administration.exception.BloomAdministrationException;
import kz.bloooom.administration.facade.AdditionalElementsFacade;
import kz.bloooom.administration.service.AdditionalElementsPriceService;
import kz.bloooom.administration.service.AdditionalElementsService;
import kz.bloooom.administration.service.BranchDivisionService;
import kz.bloooom.administration.service.EmployeeService;
import kz.bloooom.administration.util.JwtUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdditionalElementsFacadeImpl implements AdditionalElementsFacade {
    AdditionalElementsService additionalElementsService;
    EmployeeService employeeService;
    AdditionalElementsPriceService additionalElementsPriceService;
    BranchDivisionService branchDivisionService;
    AdditionalElementPriceInfoDtoConverter additionalElementPriceInfoDtoConverter;
    AdditionalElementsCreateDtoConverter additionalElementsCreateDtoConverter;
    AdditionalElementsInfoDtoConverter additionalElementsInfoDtoConverter;


    @Override
    @Transactional
    public void create(AdditionalElementsCreateDto dto) {
        AdditionalElements additionalElements = additionalElementsService
                .create(additionalElementsCreateDtoConverter.convert(dto));

        AdditionalElementsPrice additionalElementsPrice = createPrice(dto, additionalElements);
        additionalElementsPriceService.create(additionalElementsPrice);
    }

    @Override
    @Transactional
    public void addAdditionalElementToBranch(AdditionalElementAddBranchDto dto) {
        AdditionalElementsPrice additionalElementsPrice = creteAdditionalElementsPrice(dto);
        additionalElementsPriceService.create(additionalElementsPrice);
    }

    private AdditionalElementsPrice creteAdditionalElementsPrice(AdditionalElementAddBranchDto dto) {
        AdditionalElements additionalElement = additionalElementsService.getById(dto.getAdditionalElementId());
        BranchDivision branchDivision = branchDivisionService.getById(dto.getBranchDivisionId());

        return AdditionalElementsPrice
                .builder()
                .additionalElements(additionalElement)
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
    public void addAdditionalElementPrice(AdditionalElementAddBranchDto dto) {
        boolean isOverlap = additionalElementsPriceService.existsByDateOverlap(
                dto.getAdditionalElementId(),
                dto.getBranchDivisionId(),
                dto.getValidFrom().atStartOfDay(),
                dto.getValidTo().atStartOfDay());

        if (isOverlap) {
            throw new BloomAdministrationException(
                    HttpStatus.NOT_FOUND,
                    ErrorCodeConstant.PRICE_IN_THIS_RANGE_EXITS_DOEST_EXISTS,
                    "messages.exception.price-for-date-exist", dto.getValidFrom(), dto.getValidTo());
        }
        AdditionalElementsPrice additionalElementsPrice = creteAdditionalElementsPrice(dto);

        additionalElementsPriceService.create(additionalElementsPrice);
    }

    private AdditionalElementsPrice createPrice(AdditionalElementsCreateDto dto,
                                                AdditionalElements additionalElement) {
        BranchDivision branchDivision = Objects.nonNull(dto.getBranchDivisionId()) ?
                branchDivisionService.getById(dto.getBranchDivisionId()) : null;
        Employee employee = employeeService.getById(dto.getEmployeeId());

        return AdditionalElementsPrice
                .builder()
                .additionalElements(additionalElement)
                .branchDivision(branchDivision)
                .price(dto.getPrice())
                .employee(employee)
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
    public AdditionalElementsInfoDto getById(Long id) {
        return additionalElementsInfoDtoConverter.convert(additionalElementsService.getById(id));
    }

    @Override
    public List<AdditionalElementsPriceInfoDto> getByBranchIdAndElementId(Long branchId, Long elementId) {
        List<AdditionalElementsPrice> additionalElementsPrices =
                additionalElementsPriceService.getAdditionalElementsByBranchIdAndElementId(branchId, elementId);
        return additionalElementPriceInfoDtoConverter.convert(additionalElementsPrices);
    }

    @Override
    public List<AdditionalElementsBranchInfoDto> getAllByBranchId(Long branchId) {
        List<AdditionalElementsPrice> additionalElementsPrices = additionalElementsPriceService.findAllByBranchDivisionId(branchId);

        return additionalElementsPrices.stream()
                .map(this::mapToAdditionalElementsBranchInfoDto)
                .collect(Collectors.toList());
    }

    private AdditionalElementsBranchInfoDto mapToAdditionalElementsBranchInfoDto(AdditionalElementsPrice source) {
        AdditionalElementsBranchInfoDto target = new AdditionalElementsBranchInfoDto();

        target.setId(source.getAdditionalElements() != null ? source.getAdditionalElements().getId() : null);
        target.setName(source.getAdditionalElements() != null ? source.getAdditionalElements().getName() : null);
        target.setDescription(source.getAdditionalElements() != null ? source.getAdditionalElements().getDescription() : null);
        target.setPrice(source.getPrice());
        target.setCurrency(source.getCurrency() != null ? source.getCurrency().getTitle() : null);
        target.setValidFrom(source.getValidFrom());
        target.setValidTo(source.getValidTo());
        target.setExample(source.getAdditionalElements() != null ? source.getAdditionalElements().getExample() : null);
        target.setUnitOfMeasurement(source.getAdditionalElements() != null ? source.getAdditionalElements().getUnitOfMeasurement() : null);

        return target;
    }

    @Override
    public List<AdditionalElementsInfoDto> getAll() {
        return additionalElementsInfoDtoConverter.convert(additionalElementsService.getAll());
    }

    @Override
    @Transactional
    public void deletePrice(Long priceId) {
        additionalElementsPriceService.deleteById(priceId);
    }

    @Override
    @Transactional
    public void updatePrice(Long id, AdditionalElementUpdateDto dto) {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime dtoValidFrom = dto.getValidFrom().atStartOfDay();
        LocalDateTime dtoValidTo = dto.getValidTo().atStartOfDay();
        AdditionalElementsPrice existingPrice = additionalElementsPriceService.getById(id);

        // 1. Изменение только цены
        if (!dto.getPrice().equals(existingPrice.getPrice()) && dto.getValidFrom().equals(existingPrice.getValidFrom()) && dto.getValidTo().equals(existingPrice.getValidTo())) {
            if (existingPrice.getValidFrom().isAfter(today) || existingPrice.getValidFrom().isEqual(today)) {
                existingPrice.setPrice(dto.getPrice());
                additionalElementsPriceService.create(existingPrice);
            } else {
                throw new IllegalArgumentException("Cannot change price for past periods");
            }
        }

        // 2. Изменение только даты окончания
        if (!dto.getValidTo().equals(existingPrice.getValidTo()) && dto.getPrice().equals(existingPrice.getPrice()) && dto.getValidFrom().equals(existingPrice.getValidFrom())) {
            if (existingPrice.getValidTo().isAfter(today) || existingPrice.getValidTo().isEqual(today)) {
                if (dtoValidTo.isAfter(existingPrice.getValidFrom()) || dtoValidTo.isEqual(existingPrice.getValidFrom())) {
                    existingPrice.setValidTo(dtoValidTo);
                    additionalElementsPriceService.create(existingPrice);
                } else {
                    throw new IllegalArgumentException("Invalid valid_to date");
                }
            } else {
                throw new IllegalArgumentException("Cannot change past valid_to dates");
            }
        }

        // 3. Изменение только даты начала
        if (!dto.getValidFrom().equals(existingPrice.getValidFrom())
                && dto.getPrice().equals(existingPrice.getPrice()) && dto.getValidTo().equals(existingPrice.getValidTo())) {
            if (existingPrice.getValidFrom().isAfter(today) || existingPrice.getValidFrom().isEqual(today)) {
                if (dtoValidFrom.isBefore(existingPrice.getValidTo()) || dtoValidFrom.isEqual(existingPrice.getValidTo())) {
                    existingPrice.setValidFrom(dtoValidFrom);
                    additionalElementsPriceService.create(existingPrice);
                } else {
                    throw new IllegalArgumentException("Invalid valid_from date");
                }
            } else {
                throw new IllegalArgumentException("Cannot change past valid_from dates");
            }
        }

        // 4. Изменение цены и даты окончания
        if (!dto.getPrice().equals(existingPrice.getPrice()) &&
                !dto.getValidTo().equals(existingPrice.getValidTo()) && dto.getValidFrom().equals(existingPrice.getValidFrom())) {
            if (existingPrice.getValidFrom().isAfter(today) || existingPrice.getValidFrom().isEqual(today)) {
                existingPrice.setPrice(dto.getPrice());
                existingPrice.setValidTo(dtoValidTo);
                additionalElementsPriceService.create(existingPrice);
            } else {
                throw new IllegalArgumentException("Cannot change past periods");
            }
        }

        // 5. Изменение цены и даты начала
        if (!dto.getPrice().equals(existingPrice.getPrice()) &&
                !dto.getValidFrom().equals(existingPrice.getValidFrom()) && dto.getValidTo().equals(existingPrice.getValidTo())) {
            if (existingPrice.getValidFrom().isAfter(today) || existingPrice.getValidFrom().isEqual(today)) {
                if (dtoValidFrom.isBefore(existingPrice.getValidTo()) || dtoValidFrom.isEqual(existingPrice.getValidTo())) {
                    existingPrice.setPrice(dto.getPrice());
                    existingPrice.setValidFrom(dtoValidFrom);
                    additionalElementsPriceService.create(existingPrice);
                } else {
                    throw new IllegalArgumentException("Invalid valid_from date");
                }
            } else if (existingPrice.getValidFrom().isBefore(today) &&
                    dtoValidFrom.isAfter(today) && dtoValidTo.isAfter(today)) {
                // Создание новой записи
                AdditionalElementsPrice newPrice = new AdditionalElementsPrice();
                newPrice.setPrice(dto.getPrice());
                newPrice.setAdditionalElements(existingPrice.getAdditionalElements());
                newPrice.setBranchDivision(existingPrice.getBranchDivision());
                newPrice.setCurrency(existingPrice.getCurrency());
                newPrice.setCreatedBy(JwtUtils.getKeycloakId());
                newPrice.setUpdatedBy(JwtUtils.getKeycloakId());
                newPrice.setValidFrom(dtoValidFrom);
                newPrice.setValidTo(dtoValidTo);

                existingPrice.setValidTo(dtoValidTo.minusDays(1));
                additionalElementsPriceService.create(existingPrice);
                additionalElementsPriceService.create(newPrice);
            } else {
                throw new IllegalArgumentException("Invalid operation");
            }
        }

        // 6. Изменение цены, даты начала и даты окончания
        if (!dto.getPrice().equals(existingPrice.getPrice()) &&
                !dto.getValidFrom().equals(existingPrice.getValidFrom()) && !dto.getValidTo().equals(existingPrice.getValidTo())) {
            if (dtoValidFrom.isAfter(today) || dtoValidFrom.isEqual(today)) {
                existingPrice.setPrice(dto.getPrice());
                existingPrice.setValidFrom(dtoValidFrom);
                existingPrice.setValidTo(dtoValidTo);
                additionalElementsPriceService.create(existingPrice);
            } else if (existingPrice.getValidFrom().isBefore(today) &&
                    existingPrice.getValidTo().isAfter(today) &&
                    dtoValidTo.isAfter(today.minusDays(1))) {
                // Создание новой записи
                AdditionalElementsPrice newPrice = new AdditionalElementsPrice();
                newPrice.setPrice(dto.getPrice());
                newPrice.setAdditionalElements(existingPrice.getAdditionalElements());
                newPrice.setBranchDivision(existingPrice.getBranchDivision());
                newPrice.setCurrency(existingPrice.getCurrency());
                newPrice.setCreatedBy(JwtUtils.getKeycloakId());
                newPrice.setUpdatedBy(JwtUtils.getKeycloakId());
                newPrice.setValidFrom(dtoValidFrom);
                newPrice.setValidTo(dtoValidTo);

                existingPrice.setValidTo(dtoValidFrom.minusDays(1));
                additionalElementsPriceService.create(existingPrice);
                additionalElementsPriceService.create(newPrice);
            } else {
                throw new IllegalArgumentException("Invalid operation");
            }
        }
    }
}
