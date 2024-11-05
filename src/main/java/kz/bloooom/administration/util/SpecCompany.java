package kz.bloooom.administration.util;

import kz.bloooom.administration.domain.entity.Company;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.validation.constraints.NotNull;

@UtilityClass
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SpecCompany {

    public static Specification<Company> isDeleted() {
        return (r, cq, cb) -> cb.equal(r.get("isDeleted"), false);
    }

    public Specification<Company> queryCompanySearch(String query) {
        Specification<Company> resultSpec = new SpecificationBuilder<>();

        resultSpec.and(isDeleted());

        if (StringUtils.isNotBlank(query)) {
            resultSpec = resultSpec.and(organizationQueryFilter(query));
        }
        return resultSpec;
    }

    public Specification<Company> organizationQueryFilter(String query) {
        return (root, cq, cb) -> cb.and(cb.or(
                        cb.like(cb.lower(root.get(Company.Fields.name)
                                .as(String.class)), getPattern(query)),

                        cb.like(cb.lower(root.get(Company.Fields.bin)
                                .as(String.class)), getPattern(query)),

                        cb.like(root.get(Company.Fields.phoneNumber), query),

                        cb.like(cb.lower(root.get(Company.Fields.email)
                                .as(String.class)), getPattern(query)),

                        cb.like(cb.lower(root.get(Company.Fields.address)
                                .as(String.class)), getPattern(query))
                )
        );
    }

    @NotNull
    private static String getPattern(String query) {
        return "%" + query.toLowerCase() + "%";
    }

}
