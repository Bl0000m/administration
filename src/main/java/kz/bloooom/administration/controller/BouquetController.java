package kz.bloooom.administration.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bloooom.administration.domain.dto.bouquet.BouquetAddBranchDto;
import kz.bloooom.administration.domain.dto.bouquet.BouquetBranchInfoDto;
import kz.bloooom.administration.domain.dto.bouquet.BouquetCreateDto;
import kz.bloooom.administration.domain.dto.bouquet.BouquetDeletePriceDto;
import kz.bloooom.administration.domain.dto.bouquet.BouquetDetailInfoDto;
import kz.bloooom.administration.domain.dto.bouquet.BouquetInfoDto;
import kz.bloooom.administration.facade.BouquetFacade;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
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
    @PreAuthorize("@keycloak.hasAnyRole('SUPER_ADMIN', 'FLORIST')")
    public ResponseEntity<Void> create(
            @Valid @RequestPart(name = "dto") BouquetCreateDto bouquetCreateDto,
            @RequestParam(name = "files") List<MultipartFile> files) {
        bouquetFacade.createBouquet(bouquetCreateDto, files);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add")
    @PreAuthorize("@keycloak.hasAnyRole('SUPER_ADMIN', 'FLORIST')")
    @Operation(summary = "Присоединиться к существующему букету")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Void> create(@Valid @RequestBody BouquetAddBranchDto addBranchToBouquet) {
        bouquetFacade.addBranchToBouquet(addBranchToBouquet);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить букет по id")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<BouquetDetailInfoDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(bouquetFacade.getById(id));
    }

    @GetMapping("/branch/{branchId}")
    @Operation(summary = "Получить букеты филиала по его id")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<BouquetBranchInfoDto>> getByBranchId(@PathVariable Long branchId) {
        return ResponseEntity.ok(bouquetFacade.getAllByBranchId(branchId));
    }


    @GetMapping
    @Operation(summary = "Получить список всех букетов")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<BouquetInfoDto>> getAllBouquets() {
        return ResponseEntity.ok(bouquetFacade.getAll());
    }

    @DeleteMapping("/delete-price")
    @PreAuthorize("@keycloak.hasAnyRole('SUPER_ADMIN', 'FLORIST')")
    @Operation(summary = "Удалить цену букета")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Void> deletePrice(@Valid @RequestBody BouquetDeletePriceDto dto) {
        bouquetFacade.deletePrice(dto);
        return ResponseEntity.ok().build();
    }
}
