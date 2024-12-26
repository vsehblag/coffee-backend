package com.dealfinder.dealfindercommon.repository;

import com.dealfinder.dealfindercommon.dto.sale.conditions.ProgressConditionStatusDto;
import com.dealfinder.dealfindercommon.model.ProgressCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProgressConditionRepository extends JpaRepository<ProgressCondition, Long> {

    @Query(value = """
            SELECT new com.dealfinder.dealfindercommon.dto.sale.conditions.ProgressConditionStatusDto(pc.productName, pc.neededAmount, upc.currentAmount, upc.completed)
            FROM ProgressCondition pc
                     JOIN UserProgressCondition upc on pc.id = upc.progressCondition.id
            where pc.sale.id = :saleId and upc.userSale.user.id = :userId
           """)
    Set<ProgressConditionStatusDto> getProgressConditionsBySaleIdAndUserId(Long saleId, Long userId);

}
