package com.dealfinder.dealfindercommon.repository;

import com.dealfinder.dealfindercommon.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
}
