package com.task.springshop.exception;

import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class ErrorResponse {
    Instant timestamp = Instant.now();
    private List<String> errors;

    public ErrorResponse(List<String> errors) {
        this.errors = errors;
    }
}
