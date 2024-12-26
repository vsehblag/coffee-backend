package com.dealfinder.dealfindercommon.repository;

import com.dealfinder.dealfindercommon.model.UserSale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSaleRepository extends JpaRepository<UserSale, Long> {
    List<UserSale> findByUser_Id(Long userId);
    List<UserSale> findUserSaleByCompletedIsTrueAndUser_Id(Long userId);

    List<UserSale> findByUser_IdAndSale_Id(Long userId, Long SaleId);
    List<UserSale> findByUser_IdAndSale_IdAndCompletedIsFalse(Long userId, Long SaleId);
}
