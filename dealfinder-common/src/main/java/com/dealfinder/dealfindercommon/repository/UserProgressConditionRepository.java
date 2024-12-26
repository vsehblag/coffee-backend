package com.dealfinder.dealfindercommon.repository;

import com.dealfinder.dealfindercommon.model.UserProgressCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProgressConditionRepository extends JpaRepository<UserProgressCondition, Long> {
    List<UserProgressCondition> findAllByUserSale_Sale_IdAndUserSale_User_IdAndCompletedIsFalse(Long saleId, Long id);
}
