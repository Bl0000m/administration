package kz.bloooom.administration.converter.branch_division;

import kz.bloooom.administration.domain.dto.branch_division.BranchDivisionInfoDto;
import kz.bloooom.administration.domain.entity.BranchDivision;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BranchDivisionInfoDtoConverter {
    public BranchDivisionInfoDto convert(BranchDivision source) {
        return BranchDivisionInfoDto.builder()
                .id(source.getId())
                .address(source.getAddress())
                .divisionType(source.getDivisionType())
                .phoneNumber(source.getPhoneNumber())
                .email(source.getEmail())
                .build();
    }

    public List<BranchDivisionInfoDto> convert(List<BranchDivision> s) {
        return CollectionUtils.isEmpty(s) ?
                Collections.emptyList() :
                s.stream().map(this::convert).collect(Collectors.toList());
    }
}
