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
        LocalDateTime today = LocalDateTime.now().toLocalDate().atStartOfDay();
        LocalDateTime dtoValidFrom = dto.getValidFrom().atStartOfDay();
        LocalDateTime dtoValidTo = dto.getValidTo().atStartOfDay();
        AdditionalElementsPrice existingPrice = additionalElementsPriceService.getById(id);

        // 1. Изменение только цены
        if (!dto.getPrice().equals(existingPrice.getPrice()) && dtoValidFrom.equals(existingPrice.getValidFrom())
                && dtoValidTo.equals(existingPrice.getValidTo())) {
            if (existingPrice.getValidFrom().isAfter(today) || existingPrice.getValidFrom().isEqual(today)) {
                existingPrice.setPrice(dto.getPrice());
                log.info("Изменение только цены");
                additionalElementsPriceService.create(existingPrice);
                return;
            } else {
                throw new IllegalArgumentException("Cannot change price for past periods");
            }
        }

        // 2. Изменение только даты окончания
        if (!dtoValidTo.equals(existingPrice.getValidTo()) &&
                dto.getPrice().equals(existingPrice.getPrice()) &&
                dtoValidFrom.equals(existingPrice.getValidFrom())) {

            // Проверяем, что validTo еще актуален
            if (existingPrice.getValidTo().isAfter(today) || existingPrice.getValidTo().isEqual(today)) {

                // Проверяем, что новый validTo ≥ validFrom
                if (dtoValidTo.isAfter(existingPrice.getValidFrom()) || dtoValidTo.isEqual(existingPrice.getValidFrom())) {

                    boolean validCase = false;

                    // 1. Новый validTo равен (сегодня минус 1 день) и меньше существующего validTo
                    if (dtoValidTo.equals(today.minusDays(1)) && dtoValidTo.isBefore(existingPrice.getValidTo())) {
                        validCase = true;
                    }
                    // 2. Новый validTo равен сегодняшней дате и меньше существующего validTo
                    else if (dtoValidTo.equals(today) && dtoValidTo.isBefore(existingPrice.getValidTo())) {
                        validCase = true;
                    }
                    // 3. Новый validTo больше сегодняшней даты, но меньше существующего validTo
                    else if (dtoValidTo.isAfter(today) && dtoValidTo.isBefore(existingPrice.getValidTo())) {
                        validCase = true;
                    }
                    // 4. Новый validTo больше существующего validTo
                    else if (dtoValidTo.isAfter(existingPrice.getValidTo())) {
                        // Проверяем, что в расширенном периоде нет установленных цен
                        boolean priceConflict = additionalElementsPriceService.existsByDateOverlap(
                                existingPrice.getAdditionalElements().getId(),
                                existingPrice.getBranchDivision().getId(),
                                existingPrice.getValidTo(), // период расширения начинается от текущего validTo
                                dtoValidTo,
                                existingPrice.getId()
                                                                                                  );
                        if (!priceConflict) {
                            validCase = true;
                        } else {
                            throw new IllegalArgumentException("На расширенный период уже установлена другая цена");
                        }
                    }

                    if (!validCase) {
                        throw new IllegalArgumentException("Новая дата valid_to не соответствует требованиям");
                    }

                    // Обновляем дату validTo
                    existingPrice.setValidTo(dtoValidTo);
                    additionalElementsPriceService.create(existingPrice);

                    log.info("Изменение только даты окончания");
                    return;
                } else {
                    throw new IllegalArgumentException("Invalid valid_to date");
                }
            } else {
                throw new IllegalArgumentException("Cannot change past valid_to dates");
            }
        }

        // 3. Изменение только даты начала
        if (!dtoValidFrom.equals(existingPrice.getValidFrom())
                && dto.getPrice().equals(existingPrice.getPrice())
                && dtoValidTo.equals(existingPrice.getValidTo())) {

            // Проверяем, что существующий validFrom >= сегодня
            if (existingPrice.getValidFrom().isAfter(today) || existingPrice.getValidFrom().isEqual(today)) {

                // Проверяем, что новый validFrom >= today
                if (dtoValidFrom.isBefore(today)) {
                    throw new IllegalArgumentException("Cannot set valid_from to a past date");
                }

                // Проверяем, что новый validFrom <= validTo
                if (dtoValidFrom.isBefore(existingPrice.getValidTo()) || dtoValidFrom.isEqual(existingPrice.getValidTo())) {

                    // Если новая дата validFrom меньше текущей, проверяем, свободен ли этот период
                    if (dtoValidFrom.isBefore(existingPrice.getValidFrom())) {
                        boolean priceConflict = additionalElementsPriceService.existsByDateOverlap(
                                existingPrice.getAdditionalElements().getId(),
                                existingPrice.getBranchDivision().getId(),
                                dtoValidFrom,
                                existingPrice.getValidTo(), // Проверяем до validTo
                                existingPrice.getId() // Исключаем текущую запись
                                                                                                  );
                        if (priceConflict) {
                            throw new IllegalArgumentException("На этот период уже установлена другая цена");
                        }
                    }

                    // Обновляем validFrom
                    existingPrice.setValidFrom(dtoValidFrom);
                    additionalElementsPriceService.create(existingPrice); // Используем update()

                    log.info("Изменение только даты начала");
                    return;
                } else {
                    throw new IllegalArgumentException("Invalid valid_from date");
                }
            } else {
                throw new IllegalArgumentException("Cannot change past valid_from dates");
            }
        }

        // 4. Изменение цены и даты окончания
        if (!dto.getPrice().equals(existingPrice.getPrice()) &&
                !dtoValidTo.equals(existingPrice.getValidTo()) &&
                dtoValidFrom.equals(existingPrice.getValidFrom())) {

            // Проверяем, что validFrom >= сегодня
            if (existingPrice.getValidFrom().isAfter(today) || existingPrice.getValidFrom().isEqual(today)) {

                // Если новый validTo больше текущего validTo, проверяем, свободен ли этот период
                if (dtoValidTo.isAfter(existingPrice.getValidTo())) {
                    boolean priceConflict = additionalElementsPriceService.existsByDateOverlap(
                            existingPrice.getAdditionalElements().getId(),
                            existingPrice.getBranchDivision().getId(),
                            existingPrice.getValidTo().plusDays(1), // Проверяем диапазон после текущего validTo
                            dtoValidTo,
                            existingPrice.getId() // Исключаем текущую запись
                                                                                              );
                    if (priceConflict) {
                        throw new IllegalArgumentException("На этот период уже установлена другая цена");
                    }

                    // Обновляем цену и дату окончания
                    existingPrice.setPrice(dto.getPrice());
                    existingPrice.setValidTo(dtoValidTo);
                    additionalElementsPriceService.create(existingPrice); // Используем update()

                    log.info("Изменение цены и даты окончания");
                    return;

                } else if (dtoValidTo.isBefore(existingPrice.getValidTo())) {
                    // Если новый validTo меньше текущего, просто обновляем запись
                    existingPrice.setPrice(dto.getPrice());
                    existingPrice.setValidTo(dtoValidTo);
                    additionalElementsPriceService.create(existingPrice); // Используем update()

                    log.info("Изменение цены и даты окончания: validTo уменьшен");
                    return;
                } else {
                    throw new IllegalArgumentException("Invalid valid_to date");
                }
            } else {
                throw new IllegalArgumentException("Cannot change past periods");
            }
        }

        // 5. Проверка на изменение цены и даты
        if (!dto.getPrice().equals(existingPrice.getPrice()) &&
                !dtoValidFrom.equals(existingPrice.getValidFrom()) &&
                dtoValidTo.equals(existingPrice.getValidTo())) {

            // Проверяем выполнение условий для обновления цены
            if ((dtoValidFrom.isBefore(existingPrice.getValidTo()) || dtoValidFrom.isEqual(existingPrice.getValidTo()))
                    && (dtoValidFrom.isAfter(today) || dtoValidFrom.isEqual(today))
                    && (existingPrice.getValidTo().isAfter(today) || existingPrice.getValidTo().isEqual(today))) {

                // Если existingValidFrom < today, создаем новую запись и изменяем validTo у старой
                if (existingPrice.getValidFrom().isBefore(today)) {

                    // Проверяем, нет ли уже цен в этом периоде
                    boolean priceConflict = additionalElementsPriceService.existsByDateOverlap(
                            existingPrice.getAdditionalElements().getId(),
                            existingPrice.getBranchDivision().getId(),
                            dtoValidFrom,
                            existingPrice.getValidTo(),
                            existingPrice.getId() // Исключаем текущую запись
                                                                                              );
                    if (priceConflict) {
                        throw new IllegalArgumentException("На этот период уже установлена другая цена");
                    }

                    // Создаем новую запись
                    AdditionalElementsPrice newPrice = new AdditionalElementsPrice();
                    newPrice.setPrice(dto.getPrice());
                    newPrice.setAdditionalElements(existingPrice.getAdditionalElements());
                    newPrice.setBranchDivision(existingPrice.getBranchDivision());
                    newPrice.setCurrency(existingPrice.getCurrency());
                    newPrice.setCreatedBy(JwtUtils.getKeycloakId());
                    newPrice.setUpdatedBy(JwtUtils.getKeycloakId());
                    newPrice.setValidFrom(dtoValidFrom);
                    newPrice.setValidTo(existingPrice.getValidTo()); // Берем validTo из существующей записи

                    // Обновляем validTo у старой записи
                    existingPrice.setValidTo(dtoValidFrom.minusDays(1));
                    additionalElementsPriceService.create(existingPrice); // Используем update()

                    // Сохраняем новую запись
                    additionalElementsPriceService.create(newPrice);

                    log.info("Изменение цены и даты начала: создана новая запись");
                    return;
                }

                // Проверяем, нет ли других цен на этот период
                boolean priceConflict = additionalElementsPriceService.existsByDateOverlap(
                        existingPrice.getAdditionalElements().getId(),
                        existingPrice.getBranchDivision().getId(),
                        dtoValidFrom,
                        dtoValidTo,
                        existingPrice.getId() // Исключаем текущую запись
                                                                                          );
                if (priceConflict) {
                    throw new IllegalArgumentException("На этот период уже установлена другая цена");
                }

                // Изменение цены в текущей записи
                existingPrice.setPrice(dto.getPrice());
                existingPrice.setValidFrom(dtoValidFrom);
                additionalElementsPriceService.create(existingPrice); // Используем update()

                log.info("Изменение цены и даты начала: обновлена текущая запись");
            } else {
                throw new IllegalArgumentException("Invalid operation");
            }
        }

        // 6. Изменение цены, даты начала и даты окончания
        if (!dto.getPrice().equals(existingPrice.getPrice()) &&
                !dtoValidFrom.equals(existingPrice.getValidFrom()) &&
                !dtoValidTo.equals(existingPrice.getValidTo())) {

            // Проверяем, что validFrom >= сегодня
            if (dtoValidFrom.isAfter(today) || dtoValidFrom.isEqual(today)) {

                // Если existingValidFrom < today, но existingValidTo >= today и dtoValidTo >= (today - 1)
                if (existingPrice.getValidFrom().isBefore(today) &&
                        existingPrice.getValidTo().isAfter(today)) {

                    // Проверяем, нет ли уже цен в этом периоде
                    boolean priceConflict = additionalElementsPriceService.existsByDateOverlap(
                            existingPrice.getAdditionalElements().getId(),
                            existingPrice.getBranchDivision().getId(),
                            dtoValidFrom,
                            dtoValidTo,
                            existingPrice.getId() // Исключаем текущую запись
                                                                                              );
                    if (priceConflict) {
                        throw new IllegalArgumentException("На этот период уже установлена другая цена");
                    }

                    // Создание новой записи
                    AdditionalElementsPrice newPrice = new AdditionalElementsPrice();
                    newPrice.setPrice(dto.getPrice());
                    newPrice.setAdditionalElements(existingPrice.getAdditionalElements());
                    newPrice.setBranchDivision(existingPrice.getBranchDivision());
                    newPrice.setCurrency(existingPrice.getCurrency());
                    newPrice.setCreatedBy(JwtUtils.getKeycloakId());
                    newPrice.setUpdatedBy(JwtUtils.getKeycloakId());
                    newPrice.setValidFrom(dtoValidFrom);
                    newPrice.setValidTo(dtoValidTo); // Исправлено: validTo = dtoValidTo

                    // Обновляем текущую запись (validTo = dtoValidFrom - 1 день)
                    existingPrice.setValidTo(dtoValidFrom.minusDays(1));
                    additionalElementsPriceService.create(existingPrice); // Используем update()

                    // Сохраняем новую запись
                    additionalElementsPriceService.create(newPrice);

                    log.info("Изменение цены, даты начала и даты окончания");
                    return;
                }

                // Если existingValidFrom >= today, проверяем занятость периода
                if (existingPrice.getValidFrom().isAfter(today) || existingPrice.getValidFrom().isEqual(today)) {
                    boolean priceConflict = additionalElementsPriceService.existsByDateOverlap(
                            existingPrice.getAdditionalElements().getId(),
                            existingPrice.getBranchDivision().getId(),
                            dtoValidFrom,
                            dtoValidTo,
                            existingPrice.getId()
                                                                                              );
                    if (priceConflict) {
                        throw new IllegalArgumentException("На этот период уже установлена другая цена");
                    }

                    // Полное обновление записи
                    existingPrice.setPrice(dto.getPrice());
                    existingPrice.setValidFrom(dtoValidFrom);
                    existingPrice.setValidTo(dtoValidTo);
                    additionalElementsPriceService.create(existingPrice);

                    log.info("Обновление существующей записи с изменением цены, даты начала и даты окончания");
                    return;
                }

                throw new IllegalArgumentException("Invalid operation");
            } else {
                throw new IllegalArgumentException("Invalid valid_from date");
            }
        }
    }
}
