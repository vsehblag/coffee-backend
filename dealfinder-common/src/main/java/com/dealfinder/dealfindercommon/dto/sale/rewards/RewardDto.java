package com.dealfinder.dealfindercommon.dto.sale.rewards;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RewardDto {
    public Long rewardId;
    public String rewardText;
}
