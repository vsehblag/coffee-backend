package com.dealfinder.dealfindercommon.dto.sale;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SaleShortDto {
    public Long id;
    public String name;
    public String shortDescription;
    public String establishmentName;
    public String address;
    public byte[] poster;
    public Double distanceInMeters;
}
