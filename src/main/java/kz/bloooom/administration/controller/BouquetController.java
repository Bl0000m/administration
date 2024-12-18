package kz.bloooom.administration.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bloooom.administration.domain.dto.bouquet.BouquetCreateDto;
import kz.bloooom.administration.domain.dto.bouquet.BouquetInfoDto;
import kz.bloooom.administration.domain.dto.order.OrderFillDto;
import kz.bloooom.administration.facade.BouquetFacade;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/bouquet")
@Tag(name = "Bouquet API", description = "Методы для работы с букетами")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BouquetController {
    BouquetFacade bouquetFacade;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Создание букета")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Void> create(
            @Valid @RequestPart(name = "dto") BouquetCreateDto bouquetCreateDto,
            @RequestParam(name = "files") List<MultipartFile> files) {
        bouquetFacade.createBouquet(bouquetCreateDto, files);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Operation(summary = "Получить список всех букетов")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<BouquetInfoDto>> getAllBouquets() {
        return ResponseEntity.ok(bouquetFacade.getAll());
    }
}
