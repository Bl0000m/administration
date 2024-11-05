package kz.bloooom.administration.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bloooom.administration.domain.dto.employee.EmployeeCreateDto;
import kz.bloooom.administration.facade.EmployeeFacade;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/employee")
@Tag(name = "Employee API", description = "Методы для работы с работниками")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeController {

    EmployeeFacade employeeFacade;

    @PostMapping
    @Operation(summary = "Создать сотрудника")
    public ResponseEntity<Void> create(@Valid @RequestBody EmployeeCreateDto dto) {
        log.info("POST: /v1/employee to create user form web admin");
        employeeFacade.create(dto);
        return ResponseEntity.ok().build();
    }
}
