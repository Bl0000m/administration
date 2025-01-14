package kz.bloooom.administration.converter.branch_division;

import kz.bloooom.administration.domain.dto.branch_division.BranchDivisionInfoDto;
import kz.bloooom.administration.domain.dto.branch_division.BranchDivisionShortDto;
import kz.bloooom.administration.domain.entity.AdditionalElementsPrice;
import kz.bloooom.administration.domain.entity.BranchDivision;
import kz.bloooom.administration.domain.entity.FlowerVarietyPrice;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
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

    public BranchDivisionShortDto convertWithOutPrice(BranchDivision source) {
        return BranchDivisionShortDto.builder()
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

    public BranchDivisionInfoDto convert(BranchDivision source, FlowerVarietyPrice flowerVarietyPrice) {
        return BranchDivisionInfoDto.builder()
                .id(source.getId())
                .address(source.getAddress())
                .divisionType(source.getDivisionType())
                .phoneNumber(source.getPhoneNumber())
                .email(source.getEmail())
                .price(flowerVarietyPrice.getPrice())
                .currency(flowerVarietyPrice.getCurrency().getTitle())
                .validFrom(flowerVarietyPrice.getValidFrom())
                .validTo(flowerVarietyPrice.getValidTo())
                .build();
    }

    public BranchDivisionInfoDto convert(BranchDivision source, AdditionalElementsPrice additionalElementsPrice) {
        return BranchDivisionInfoDto.builder()
                .id(source.getId())
                .address(source.getAddress())
                .divisionType(source.getDivisionType())
                .phoneNumber(source.getPhoneNumber())
                .email(source.getEmail())
                .price(additionalElementsPrice.getPrice())
                .currency(additionalElementsPrice.getCurrency().getTitle())
                .validFrom(additionalElementsPrice.getValidFrom())
                .validTo(additionalElementsPrice.getValidTo())
                .build();
    }

    public List<BranchDivisionInfoDto> convert(Map<FlowerVarietyPrice, BranchDivision> branchDivisionMap) {
        return CollectionUtils.isEmpty(branchDivisionMap) ? Collections.emptyList() :
                branchDivisionMap.entrySet().stream()
                        .map(entry -> convert(entry.getValue(), entry.getKey()))
                        .collect(Collectors.toList());
    }

    public List<BranchDivisionInfoDto> convertAdditionalElementPriceMap(Map<AdditionalElementsPrice, BranchDivision> branchDivisionMap) {
        return CollectionUtils.isEmpty(branchDivisionMap) ? Collections.emptyList() :
                branchDivisionMap.entrySet().stream()
                        .map(entry -> convert(entry.getValue(), entry.getKey()))
                        .collect(Collectors.toList());
    }

}
