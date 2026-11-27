package com.javabuilder.backendservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ErrorResponse {
    private String code;
    private String message;
    private Instant timestamp;
    private String path;
}