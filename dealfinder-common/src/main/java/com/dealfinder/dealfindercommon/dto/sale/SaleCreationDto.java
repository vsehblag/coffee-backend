package com.dealfinder.dealfindercommon.dto.sale;


import com.dealfinder.dealfindercommon.dto.sale.conditions.ManualConditionStatusDto;
import com.dealfinder.dealfindercommon.dto.sale.conditions.ProgressConditionStatusDto;
import com.dealfinder.dealfindercommon.dto.sale.rewards.RewardDto;
import lombok.*;

import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SaleCreationDto {
    private String name;
    //TODO поменять dto-шки для создания кондишенов и наград
    private Set<ProgressConditionStatusDto> progressConditions;
    private Set<ManualConditionStatusDto> manualConditions;
    private Set<RewardDto> rewards;
    private String description;
    private String shortDescription;
    private byte[] poster;
}
