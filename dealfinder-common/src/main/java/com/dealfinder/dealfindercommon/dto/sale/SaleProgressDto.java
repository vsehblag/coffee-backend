package com.dealfinder.dealfindercommon.dto.sale;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SaleProgressDto {
    private Long id;
    private String name;
    private Long numCompleteConditions;
    private Long numTotalConditions;

    private String establishmentName;
    private String address;
    private Double longitude;
    private Double latitude;

    private Double distanceInMeters;
    private byte[] poster;
}
