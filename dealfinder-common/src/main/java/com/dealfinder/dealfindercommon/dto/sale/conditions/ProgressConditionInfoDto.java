package com.dealfinder.dealfindercommon.dto.sale.conditions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class ProgressConditionInfoDto {
    private String productName;
    private Integer neededAmount;
}
