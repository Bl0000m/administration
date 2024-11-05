package kz.bloooom.administration.domain.entity;

import kz.bloooom.administration.enumeration.company_type.CompanyTypeCode;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "company_type")
@FieldNameConstants
public class CompanyType extends AbstractEnumEntity<CompanyTypeCode> {

}

