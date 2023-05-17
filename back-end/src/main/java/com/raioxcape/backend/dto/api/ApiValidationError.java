package com.raioxcape.backend.dto.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiValidationError extends ApiSubError {

    private String object;

    private String field;

    private Object rejectedValue;

    private String message;

    public ApiValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }
}
