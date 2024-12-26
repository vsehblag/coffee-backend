package com.dealfinder.dealfindercommon.dto.sale.conditions;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ManualConditionInfoDto {
    private String conditionText;
}