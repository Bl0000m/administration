package kz.bloooom.administration.repository;

import kz.bloooom.administration.domain.entity.AdditionalElementsPrice;
import kz.bloooom.administration.enumeration.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AdditionalElementsPriceRepository extends JpaRepository<AdditionalElementsPrice, Long> {

    List<AdditionalElementsPrice> findAllByAdditionalElementsId(Long additionalElementId);

    @Query("SELECT a FROM AdditionalElementsPrice a " +
            "WHERE a.branchDivision.id = :branchDivisionId " +
            "AND :currentDate BETWEEN a.validFrom AND a.validTo")
    List<AdditionalElementsPrice> findAllByBranchDivisionIdAndCurrentDate(
            @Param("branchDivisionId") Long branchDivisionId,
            @Param("currentDate") LocalDateTime currentDate);

    List<AdditionalElementsPrice> findAllByBranchDivisionIdAndAdditionalElementsId(
            Long branchId,
            Long elementId);


    @Query("SELECT COUNT(aep) > 0 " +
            "FROM AdditionalElementsPrice aep " +
            "WHERE aep.additionalElements.id = :additionalElementId " +
            "AND aep.branchDivision.id = :branchDivisionId " +
            "AND ((:validFrom BETWEEN aep.validFrom AND aep.validTo) " +
            "     OR (:validTo BETWEEN aep.validFrom AND aep.validTo) " +
            "     OR (aep.validFrom BETWEEN :validFrom AND :validTo))")
    boolean existsByDateOverlap(
            @Param("additionalElementId") Long additionalElementId,
            @Param("branchDivisionId") Long branchDivisionId,
            @Param("validFrom") LocalDateTime validFrom,
            @Param("validTo") LocalDateTime validTo);

    @Query("SELECT COUNT(aep) > 0 " +
            "FROM AdditionalElementsPrice aep " +
            "WHERE aep.additionalElements.id = :additionalElementId " +
            "AND aep.branchDivision.id = :branchDivisionId " +
            "AND aep.id <> :excludeId " +
            "AND ((:validFrom BETWEEN aep.validFrom AND aep.validTo) " +
            "     OR (:validTo BETWEEN aep.validFrom AND aep.validTo) " +
            "     OR (aep.validFrom BETWEEN :validFrom AND :validTo))")
    boolean existsByDateOverlap(
            @Param("additionalElementId") Long additionalElementId,
            @Param("branchDivisionId") Long branchDivisionId,
            @Param("validFrom") LocalDateTime validFrom,
            @Param("validTo") LocalDateTime validTo,
            @Param("excludeId") Long excludeId);

    @Query("DELETE FROM AdditionalElementsPrice e WHERE e.additionalElements.id = :additionalElementId " +
            "AND e.price = :price " +
            "AND e.branchDivision.id = :branchDivisionId " +
            "AND e.currency = :currency " +
            "AND e.validFrom = :validFrom " +
            "AND e.validTo = :validTo")
    void deleteByParams(@Param("additionalElementId") Long additionalElementId,
                        @Param("price") Double price,
                        @Param("branchDivisionId") Long branchDivisionId,
                        @Param("currency") Currency currency,
                        @Param("validFrom") LocalDate validFrom,
                        @Param("validTo") LocalDate validTo);
}
