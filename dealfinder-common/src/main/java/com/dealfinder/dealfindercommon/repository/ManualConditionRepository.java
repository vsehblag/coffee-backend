package com.dealfinder.dealfindercommon.repository;

import com.dealfinder.dealfindercommon.dto.sale.conditions.ManualConditionStatusDto;
import com.dealfinder.dealfindercommon.model.ManualCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ManualConditionRepository extends JpaRepository<ManualCondition, Long> {

    @Query("SELECT new com.dealfinder.dealfindercommon.dto.sale.conditions.ManualConditionStatusDto(mc.conditionText, umc.completed) " +
            "FROM ManualCondition mc " +
            "JOIN UserManualCondition umc ON mc.id = umc.manualCondition.id " +
            "WHERE mc.sale.id = :saleId AND umc.userSale.user.id = :userId")
    Set<ManualConditionStatusDto> getManualConditionDtosBySaleIdAndUserId(Long saleId, Long userId);
}
