package kz.bloooom.administration.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bloooom.administration.domain.dto.flower.FlowerCreatDto;
import kz.bloooom.administration.facade.FlowerFacade;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/flower")
@Tag(name = "Flower API", description = "Методы для работы с цветами")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FlowerController {

    FlowerFacade flowerFacade;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Создание цветка")
    public ResponseEntity<Void> create(
            @Valid @RequestPart(name = "dto") FlowerCreatDto bouquetCreateDto,
            @RequestParam(name = "file") MultipartFile file) {
        flowerFacade.createFlower(bouquetCreateDto, file);
        return ResponseEntity.ok().build();
    }
}
