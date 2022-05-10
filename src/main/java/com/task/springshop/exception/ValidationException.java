package com.task.springshop.exception;

import java.util.List;

public class ValidationException extends ApplicationException{
    private final List<String> validationErrors;

    public ValidationException(List<String> errors) {
        this.validationErrors = errors;
    }

    public List<String> getValidationMessages() {
        return validationErrors;
    }
}
