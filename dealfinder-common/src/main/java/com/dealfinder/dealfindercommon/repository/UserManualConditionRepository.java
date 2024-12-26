package com.dealfinder.dealfindercommon.repository;

import com.dealfinder.dealfindercommon.model.UserManualCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserManualConditionRepository extends JpaRepository<UserManualCondition, Long> {
    List<UserManualCondition> findAllByUserSale_Sale_IdAndUserSale_User_IdAndCompletedIsFalse(Long saleId, Long id);
}
