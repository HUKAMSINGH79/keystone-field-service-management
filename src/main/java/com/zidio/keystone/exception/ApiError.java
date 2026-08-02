package com.zidio.keystone.exception;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Map;

@Value
@Builder
public class ApiError {
    LocalDateTime timestamp;
    int status;
    String error;
    String message;
    Map<String, String> validationErrors;
}
