package kz.bloooom.administration.repository;

import kz.bloooom.administration.domain.entity.FlowerVarietyPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlowerVarietyPriceRepository extends JpaRepository<FlowerVarietyPrice, Long> {

    List<FlowerVarietyPrice> findAllByFlowerVarietyId(Long flowerVarietyId);

    @Query("SELECT COUNT(fvp) > 0 " +
            "FROM FlowerVarietyPrice fvp " +
            "WHERE fvp.flowerVariety.id = :flowerVarietyId " +
            "AND fvp.branchDivision.id = :branchDivisionId " +
            "AND ((:validFrom BETWEEN fvp.validFrom AND fvp.validTo) " +
            "     OR (:validTo BETWEEN fvp.validFrom AND fvp.validTo) " +
            "     OR (fvp.validFrom BETWEEN :validFrom AND :validTo))")
    boolean existsByDateOverlap(
            @Param("flowerVarietyId") Long flowerVarietyId,
            @Param("branchDivisionId") Long branchDivisionId,
            @Param("validFrom") LocalDateTime validFrom,
            @Param("validTo") LocalDateTime validTo
    );

    @Query("SELECT fvp FROM FlowerVarietyPrice fvp " +
            "WHERE fvp.branchDivision.id = :branchDivisionId " +
            "AND :currentDate BETWEEN fvp.validFrom AND fvp.validTo")
    List<FlowerVarietyPrice> findAllByBranchDivisionIdAndCurrentDate(
            @Param("branchDivisionId") Long branchDivisionId,
            @Param("currentDate") LocalDateTime currentDate);

    boolean existsByBranchDivisionIdAndValidFromLessThanEqualAndValidToGreaterThanEqual(
            Long branchDivisionId, LocalDateTime validFrom, LocalDateTime validTo);

    List<FlowerVarietyPrice> findAllByBranchDivisionIdAndFlowerVarietyId(Long branchId, Long varietyId);


}
