package com.dealfinder.dealfindercommon.dto.page;

import com.dealfinder.dealfindercommon.model.EstablishmentWorkingDay;
import lombok.*;

import java.util.Set;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EstablishmentSearchDto {
    public Long id;
    public String name;
    public String address;
    public String description;
    public Double longitude;
    public Double latitude;
    public byte[] poster;
    public Set<EstablishmentWorkingDay> workingDays;
    public Double distanceToUser;
}
