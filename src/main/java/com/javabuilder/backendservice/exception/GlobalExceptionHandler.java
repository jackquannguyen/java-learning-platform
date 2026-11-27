package com.javabuilder.backendservice.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@RestControllerAdvice
@Builder
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(AppException ex, HttpServletRequest request) {
        ErrorCode errCode = ex.getErrorCode();
        ErrorResponse errResponse = new ErrorResponse(errCode.getCode(), errCode.getMessage(), Instant.now(),
                request.getRequestURI());

        return ResponseEntity
                .status(errCode.getHttpStatus())
                .body(errResponse);
    }
}
