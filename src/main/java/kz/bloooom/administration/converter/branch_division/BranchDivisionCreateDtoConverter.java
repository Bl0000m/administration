package kz.bloooom.administration.converter.branch_division;

import kz.bloooom.administration.domain.dto.branch_division.BranchDivisionCreateDto;
import kz.bloooom.administration.domain.entity.BranchDivision;
import kz.bloooom.administration.service.CompanyService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BranchDivisionCreateDtoConverter {
    CompanyService companyService;

    public BranchDivision convert(BranchDivisionCreateDto source) {
        return BranchDivision.builder()
                .company(companyService.getById(source.getCompanyId()))
                .address(source.getAddress())
                .phoneNumber(source.getPhoneNumber())
                .email(source.getEmail())
                .build();
    }
}
