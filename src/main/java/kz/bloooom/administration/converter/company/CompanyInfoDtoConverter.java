package kz.bloooom.administration.converter.company;


import kz.bloooom.administration.domain.dto.company.CompanyInfoDto;
import kz.bloooom.administration.domain.dto.page.PageDTO;
import kz.bloooom.administration.domain.entity.Company;
import kz.bloooom.administration.util.PageConverterUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;


import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CompanyInfoDtoConverter {

    public CompanyInfoDto convert(Company source) {
        CompanyInfoDto target = new CompanyInfoDto();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setBin(source.getBin());
        target.setType(source.getCompanyType().getName().get("ru"));
        target.setAddress(source.getAddress());
        target.setContractID(source.getContractID());
        target.setAdditionalAddress(source.getAdditionalAddress());
        target.setPhone(source.getPhoneNumber());
        target.setEmail(source.getEmail());
        target.setWebsite(source.getWebsite());
        target.setSocialMedia(source.getSocialMedia());
        return target;
    }

    public List<CompanyInfoDto> convert(List<Company> s) {
        return CollectionUtils.isEmpty(s) ?
                Collections.emptyList() :
                s.stream().map(this::convert).collect(Collectors.toList());
    }

    public PageDTO<CompanyInfoDto> convert(Page<Company> s) {
        List<CompanyInfoDto> result = this.convert(s.getContent());
        return PageConverterUtils.convert(s, result);
    }
}
