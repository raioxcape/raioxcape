package com.raioxcape.backend.dto.api.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@JsonPropertyOrder(value = {"status", "data", "error"})
public class ApiResponse {

    private HttpStatus status;

    private Object data;

    private Object error;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private final LocalDateTime timestamp;

    public ApiResponse(HttpStatus status, Object object) {
        if (status.isError()) {
            this.error = object;
        } else {
            this.data = object;
        }

        this.status = status;
        this.timestamp = LocalDateTime.now();
    }
}
