package com.dealfinder.dealfindercommon.dto.establishment;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EstablishmentDto {
    public Long id;
    public String name;
    public String address;
    public String phoneNumber;
    public String email;
    public String socialNetwork;
    public String additionalInfo;
    public byte[] poster;
    public String description;
    public Double latitude;
    public Double longitude;
    public String login;
    public String password;
    public List<EstablishmentWorkingDayDto> workingDays;
}
