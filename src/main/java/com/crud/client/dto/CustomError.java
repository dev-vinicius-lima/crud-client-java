package com.crud.client.dto;

import java.time.Instant;
import java.util.List;

public class CustomError {
    private Instant timestamp;
    private Integer status;
    private String error;
    private String path;
    private List<ValidationError> errors;

    public CustomError(Instant timestamp, Integer status, String error, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.path = path;
    }

    public CustomError(Instant timestamp, Integer status, String error, String path, List<ValidationError> errors) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.path = path;
        this.errors = errors;
    }

    public CustomError(String message, String string) {

    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getPath() {
        return path;
    }
}
