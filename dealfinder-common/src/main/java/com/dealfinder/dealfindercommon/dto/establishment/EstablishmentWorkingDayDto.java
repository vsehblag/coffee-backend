package com.dealfinder.dealfindercommon.dto.establishment;

import lombok.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EstablishmentWorkingDayDto {
    private Integer startHour;
    private Integer endHour;

    private Integer dayOfWeek;
}
