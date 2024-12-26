package com.dealfinder.dealfindercommon.dto.page;


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
public class SalePageDto {
    private Long id;
    private String name;
    private Set<ProgressConditionStatusDto> progressConditions;
    private Set<ManualConditionStatusDto> manualConditions;
    private Set<RewardDto> rewards;
    private String description;
    private String shortDescription;
    private Long establishmentId;
    private String establishmentName;
    private String establishmentAddress;
    private Double distanceInMeters;
    private byte[] QR;
    private byte[] poster;
}
