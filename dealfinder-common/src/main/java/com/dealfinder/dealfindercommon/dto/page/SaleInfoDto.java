package com.dealfinder.dealfindercommon.dto.page;


import com.dealfinder.dealfindercommon.dto.sale.conditions.ManualConditionInfoDto;
import com.dealfinder.dealfindercommon.dto.sale.conditions.ProgressConditionInfoDto;
import com.dealfinder.dealfindercommon.dto.sale.rewards.RewardInfoDto;
import lombok.*;

import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SaleInfoDto {
    private Long id;
    private String name;
    private Set<ProgressConditionInfoDto> progressConditions;
    private Set<ManualConditionInfoDto> manualConditions;
    private Set<RewardInfoDto> rewards;
    private String description;
    private String shortDescription;
    private Long establishmentId;
    private String establishmentName;
    private String establishmentAddress;
    private byte[] QR;
    private byte[] poster;
}
