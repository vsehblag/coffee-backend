package com.dealfinder.dealfindercommon.dto.sale;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SaleStoryDto {
    public Long saleId;
    public String saleName;
    public String shortDescription;
    public String establishmentName;
    public String address;
    public byte[] salePoster;
    public Double latitude;
    public Double longitude;
    public Double distanceInMeters;
}
