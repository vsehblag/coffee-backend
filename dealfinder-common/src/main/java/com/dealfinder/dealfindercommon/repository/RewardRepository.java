package com.dealfinder.dealfindercommon.repository;

import com.dealfinder.dealfindercommon.dto.sale.rewards.RewardDto;
import com.dealfinder.dealfindercommon.model.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RewardRepository extends JpaRepository<Reward, Long> {

    @Query(value = """
            select new com.dealfinder.dealfindercommon.dto.sale.rewards.RewardDto(r.id, r.rewardText)
            FROM Reward r
            JOIN Sale s on s.id = r.sale.id
            where s.id = :saleId
           """)
    Set<RewardDto> findAllBySaleId(Long saleId);
}
