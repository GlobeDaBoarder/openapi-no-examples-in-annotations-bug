package org.example.openapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.example.openapi.dto.ErrorDto;

import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final String DEFAULT_PATTERN_MESSAGE = "must match ";
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorDto handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String errorMessage = extractValidationMessages(ex);

        logger.error("MethodArgumentNotValidException", ex);
        return new ErrorDto().code(HttpStatus.BAD_REQUEST.value()).message(errorMessage);
    }

    private String extractValidationMessages(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> Objects.equals(fieldError.getCode(), "Pattern") &&
                        fieldError.getDefaultMessage() != null &&
                        !fieldError.getDefaultMessage().startsWith(DEFAULT_PATTERN_MESSAGE) ?
                        fieldError.getDefaultMessage() :
                        fieldError.getField() + " " + fieldError.getDefaultMessage())
                .collect(Collectors.joining("; "));
    }
}
