package com.dealfinder.dealfinderprocessor.platform.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class SaleNotFoundException extends RuntimeException {
    private String message;
}
