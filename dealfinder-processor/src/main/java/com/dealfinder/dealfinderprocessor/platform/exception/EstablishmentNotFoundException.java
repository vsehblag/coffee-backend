package com.dealfinder.dealfinderprocessor.platform.exception;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class EstablishmentNotFoundException extends RuntimeException{
    private String message;
}
