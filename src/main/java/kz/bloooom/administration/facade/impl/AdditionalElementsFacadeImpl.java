package kz.bloooom.administration.facade.impl;

import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.converter.additional_elements.AdditionalElementsCreateDtoConverter;
import kz.bloooom.administration.converter.additional_elements.AdditionalElementsInfoDtoConverter;
import kz.bloooom.administration.domain.dto.additional_elements.AdditionalElementAddBranchDto;
import kz.bloooom.administration.domain.dto.additional_elements.AdditionalElementsCreateDto;
import kz.bloooom.administration.domain.dto.additional_elements.AdditionalElementsInfoDto;
import kz.bloooom.administration.domain.entity.AdditionalElements;
import kz.bloooom.administration.domain.entity.AdditionalElementsPrice;
import kz.bloooom.administration.domain.entity.BranchDivision;
import kz.bloooom.administration.domain.entity.Employee;
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
import java.util.List;
import java.util.Objects;

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
    public List<AdditionalElementsInfoDto> getAll() {
        return additionalElementsInfoDtoConverter.convert(additionalElementsService.getAll());
    }
}
