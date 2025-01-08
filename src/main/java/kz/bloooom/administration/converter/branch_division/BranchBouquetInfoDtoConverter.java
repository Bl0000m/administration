package kz.bloooom.administration.converter.branch_division;

import kz.bloooom.administration.domain.dto.branch_division.BranchBouquetInfoDto;
import kz.bloooom.administration.domain.entity.BouquetBranchPrice;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BranchBouquetInfoDtoConverter {

    public BranchBouquetInfoDto convert(BouquetBranchPrice source) {
        BranchBouquetInfoDto target = new BranchBouquetInfoDto();
        target.setBranchId(source.getBranchDivision().getId());
        target.setCompanyName(source.getBranchDivision().getCompany().getName());
        target.setPrice(source.getPrice());
        target.setAddress(source.getBranchDivision().getAddress());
        target.setEmail(source.getBranchDivision().getEmail());
        target.setPhoneNumber(source.getBranchDivision().getPhoneNumber());
        return target;
    }

    public List<BranchBouquetInfoDto> convert(List<BouquetBranchPrice> s) {
        return CollectionUtils.isEmpty(s) ?
                Collections.emptyList() :
                s.stream().map(this::convert).collect(Collectors.toList());
    }
}
