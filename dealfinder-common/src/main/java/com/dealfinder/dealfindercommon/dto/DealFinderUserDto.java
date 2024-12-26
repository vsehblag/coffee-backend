package com.dealfinder.dealfindercommon.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DealFinderUserDto {
    private Long id;
    private String login;
    private String username;
    private String password;
}
