package kz.bloooom.administration.facade.impl;

import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.converter.country.CountryInfoDtoConverter;
import kz.bloooom.administration.converter.flower.FlowerInfoDtoConverter;
import kz.bloooom.administration.converter.flower_variety.*;
import kz.bloooom.administration.converter.stem_care.StemCareInfoDtoConverter;
import kz.bloooom.administration.converter.temperature_care.TemperatureCareInfoDtoConverter;
import kz.bloooom.administration.converter.water_care.WaterCareInfoDtoConverter;
import kz.bloooom.administration.domain.dto.flower_variety.*;
import kz.bloooom.administration.domain.entity.*;
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
import java.time.LocalDateTime;
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
    FlowerVarietyPriceInfoDtoConverter flowerVarietyPriceInfoDtoConverter;
    StorageService storageService;
    EmployeeService employeeService;
    FlowerVarietyCreateDtoConverter flowerVarietyCreateDtoConverter;
    FlowerVarietyInfoDtoConverter flowerVarietyInfoDtoConverter;

    @Override
    @Transactional
    public void create(
            FlowerVarietyCreateDto dto,
            MultipartFile photo) {
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

    private FlowerVarietyPrice createPrice(
            FlowerVarietyCreateDto dto,
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

    private void addPhoto(
            FlowerVariety flowerVariety,
            MultipartFile photo) {
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
    @Transactional
    public void updatePrice(
            Long id,
            FlowerVarietyUpdateDto dto) {
        LocalDateTime today = LocalDateTime.now().toLocalDate().atStartOfDay();
        LocalDateTime dtoValidFrom = dto.getValidFrom().atStartOfDay();
        LocalDateTime dtoValidTo = dto.getValidTo().atStartOfDay();
        FlowerVarietyPrice existingPrice = flowerVarietyPriceService.getById(id);

        // 1. Изменение только цены
        if (!dto.getPrice().equals(existingPrice.getPrice()) && dtoValidFrom.equals(existingPrice.getValidFrom())
                && dtoValidTo.equals(existingPrice.getValidTo())) {
            if (existingPrice.getValidFrom().isAfter(today) || existingPrice.getValidFrom().isEqual(today)) {
                existingPrice.setPrice(dto.getPrice());
                log.info("Изменение только цены");
                flowerVarietyPriceService.create(existingPrice);
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

                    // Проверяем, нет ли других цен в этом диапазоне
                    boolean priceConflict = flowerVarietyPriceService.existsByDateOverlap(
                            existingPrice.getFlowerVariety().getId(),
                            existingPrice.getBranchDivision().getId(),
                            existingPrice.getValidFrom(),
                            dtoValidTo,
                            existingPrice.getId());
                    if (priceConflict) {
                        throw new IllegalArgumentException("На этот период уже установлена другая цена");
                    }

                    // Обновляем дату validTo
                    existingPrice.setValidTo(dtoValidTo);
                    flowerVarietyPriceService.create(existingPrice);

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
                        boolean priceConflict = flowerVarietyPriceService.existsByDateOverlap(
                                existingPrice.getFlowerVariety().getId(),
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
                    flowerVarietyPriceService.create(existingPrice); // Используем update()

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
                    boolean priceConflict = flowerVarietyPriceService.existsByDateOverlap(
                            existingPrice.getFlowerVariety().getId(),
                            existingPrice.getBranchDivision().getId(),
                            existingPrice.getValidTo().plusDays(1), // Проверяем диапазон после текущего validTo
                            dtoValidTo,
                            existingPrice.getId() // Исключаем текущую запись
                                                                                              );
                    if (priceConflict) {
                        throw new IllegalArgumentException("На этот период уже установлена другая цена");
                    }
                }

                // Обновляем цену и дату окончания
                existingPrice.setPrice(dto.getPrice());
                existingPrice.setValidTo(dtoValidTo);
                flowerVarietyPriceService.create(existingPrice); // Используем update()

                log.info("Изменение цены и даты окончания");
                return;
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
                    boolean priceConflict = flowerVarietyPriceService.existsByDateOverlap(
                            existingPrice.getFlowerVariety().getId(),
                            existingPrice.getBranchDivision().getId(),
                            dtoValidFrom,
                            existingPrice.getValidTo(),
                            existingPrice.getId() // Исключаем текущую запись
                                                                                              );
                    if (priceConflict) {
                        throw new IllegalArgumentException("На этот период уже установлена другая цена");
                    }

                    // Создаем новую запись
                    FlowerVarietyPrice newPrice = new FlowerVarietyPrice();
                    newPrice.setPrice(dto.getPrice());
                    newPrice.setFlowerVariety(existingPrice.getFlowerVariety());
                    newPrice.setBranchDivision(existingPrice.getBranchDivision());
                    newPrice.setCurrency(existingPrice.getCurrency());
                    newPrice.setCreatedBy(JwtUtils.getKeycloakId());
                    newPrice.setUpdatedBy(JwtUtils.getKeycloakId());
                    newPrice.setValidFrom(dtoValidFrom);
                    newPrice.setValidTo(existingPrice.getValidTo()); // Берем validTo из существующей записи

                    // Обновляем validTo у старой записи
                    existingPrice.setValidTo(dtoValidFrom.minusDays(1));
                    flowerVarietyPriceService.create(existingPrice); // Используем update()

                    // Сохраняем новую запись
                    flowerVarietyPriceService.create(newPrice);

                    log.info("Изменение цены и даты начала: создана новая запись");
                    return;
                }

                // Проверяем, нет ли других цен на этот период
                boolean priceConflict = flowerVarietyPriceService.existsByDateOverlap(
                        existingPrice.getFlowerVariety().getId(),
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
                flowerVarietyPriceService.create(existingPrice); // Используем update()

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
            if ((dtoValidFrom.isAfter(today) || dtoValidFrom.isEqual(today))) {

                // Если existingValidFrom < today, но existingValidTo >= today и dtoValidTo >= (today - 1)
                if (existingPrice.getValidFrom().isBefore(today) &&
                        existingPrice.getValidTo().isAfter(today) &&
                        dtoValidTo.isAfter(today.minusDays(1))) {

                    // Проверяем, нет ли уже цен в этом периоде
                    boolean priceConflict = flowerVarietyPriceService.existsByDateOverlap(
                            existingPrice.getFlowerVariety().getId(),
                            existingPrice.getBranchDivision().getId(),
                            dtoValidFrom,
                            dtoValidTo,
                            existingPrice.getId() // Исключаем текущую запись
                                                                                              );
                    if (priceConflict) {
                        throw new IllegalArgumentException("На этот период уже установлена другая цена");
                    }

                    // Создание новой записи
                    FlowerVarietyPrice newPrice = new FlowerVarietyPrice();
                    newPrice.setPrice(dto.getPrice());
                    newPrice.setFlowerVariety(existingPrice.getFlowerVariety());
                    newPrice.setBranchDivision(existingPrice.getBranchDivision());
                    newPrice.setCurrency(existingPrice.getCurrency());
                    newPrice.setCreatedBy(JwtUtils.getKeycloakId());
                    newPrice.setUpdatedBy(JwtUtils.getKeycloakId());
                    newPrice.setValidFrom(dtoValidFrom);
                    newPrice.setValidTo(dtoValidTo);

                    // Обновляем текущую запись (validTo = dtoValidFrom - 1 день)
                    existingPrice.setValidTo(dtoValidFrom.minusDays(1));
                    flowerVarietyPriceService.create(existingPrice); // Используем update()

                    // Сохраняем новую запись
                    flowerVarietyPriceService.create(newPrice);

                    log.info("Изменение цены, даты начала и даты окончания");
                } else {
                    throw new IllegalArgumentException("Invalid operation");
                }
            } else {
                throw new IllegalArgumentException("Invalid valid_from date");
            }
        }
    }
    @Override
    public List<FlowerVarietyPriceInfoDto> getByBranchIdAndVarietyId(
            Long branchId,
            Long varietyId) {
        List<FlowerVarietyPrice> flowerVarietyPrices =
                flowerVarietyPriceService.getAllByBranchIdAndFlowerVarietyId(branchId, varietyId);
        return flowerVarietyPriceInfoDtoConverter.convert(flowerVarietyPrices);
    }


    private FlowerVarietyPrice createNewEntry(
            FlowerVarietyPrice existing,
            Double price,
            LocalDateTime validFrom,
            LocalDateTime validTo) {
        return FlowerVarietyPrice.builder()
                                 .branchDivision(existing.getBranchDivision())
                                 .flowerVariety(existing.getFlowerVariety())
                                 .price(price)
                                 .validFrom(validFrom)
                                 .validTo(validTo)
                                 .currency(existing.getCurrency())
                                 .build();
    }

    private boolean hasPriceOverlap(
            FlowerVarietyPrice existing,
            LocalDateTime dtoValidTo) {
        return flowerVarietyPriceService.existsByBranchDivisionIdAndDateRange(existing.getBranchDivision().getId(),
                                                                              existing.getFlowerVariety().getId(),
                                                                              dtoValidTo, dtoValidTo);
    }

    @Transactional
    public FlowerVarietyPrice saveAndReturn(FlowerVarietyPrice entity) {
        return flowerVarietyPriceService.create(entity);
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
            target.setTemperatureCareInfo(
                    temperatureCareInfoDtoConverter.convert(source.getFlowerVariety().getTemperatureCare()));
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

    @Override
    @Transactional
    public void deletePrice(final Long priceId) {
        flowerVarietyPriceService.deleteById(priceId);
    }
}
