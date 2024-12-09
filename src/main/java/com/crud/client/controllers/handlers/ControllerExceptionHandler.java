package com.crud.client.controllers.handlers;

import com.crud.client.dto.CustomError;
import com.crud.client.dto.ValidationError;
import com.crud.client.services.exceptions.*;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> ResourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError error = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<CustomError> Database(DatabaseException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomError error = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<CustomError> NoSuchElement(NoSuchElementException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError error = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(InvalidDefinitionException.class)
    public ResponseEntity<CustomError> InvalidDefinition(InvalidDefinitionException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError error = new CustomError(Instant.now(), status.value(), "Falha de integridade.", request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CustomError> handleConstraintViolation(ConstraintViolationException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError error = new ValidationError(Instant.now(), status.value(), "Dados inválidos.", request.getRequestURI());

        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();

            error.addError(fieldName, errorMessage);
        }

        return ResponseEntity.status(status).body(error);
    }
}
