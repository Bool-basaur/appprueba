package com.example.appprueba.adapters.in.rest.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * =============================================================================
 * @Class: GlobalExceptionHandler
 * @Layer: Inbound Adapter (REST) - Exception
 * @Description: Manages different types of errors in the request response
 * =============================================================================
 * @Author Alex Jiménez Fernández
 **/
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /**
     *   Retrieves the specific 404 error
     * @param ex is the PriceNotFoundException
     * @Return HttpStatus.NOT_FOUND - Price not found with 404 code
     **/
    @ExceptionHandler(PriceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handlePriceNotFound(PriceNotFoundException ex) {
        log.warn("Price not found: {}", ex.getMessage());
        return buildResponse(HttpStatus.NOT_FOUND, "Price Not Found", ex.getMessage());
    }

    /**
     *   Retrieves the specific 400 error
     * @param ex is the Exception
     * @Return HttpStatus.BAD_REQUEST - Bad request with 400 code
     **/
    @ExceptionHandler({
            MethodArgumentTypeMismatchException.class,
            MissingServletRequestParameterException.class,
            IllegalArgumentException.class
    })
    public ResponseEntity<Map<String, Object>> handleBadRequest(Exception ex) {
        log.warn("Bad request: {}", ex.getMessage());
        return buildResponse(HttpStatus.BAD_REQUEST, "Bad Request", "Invalid parameter: " + ex.getMessage());
    }

    /**
     *   Retrieves the specific 500 error
     * @param ex is the Exception
     * @Return HttpStatus.INTERNAL_SERVER_ERROR - Internal server error with 500 code
     **/
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleUnexpectedError(Exception ex) {
        log.error("Unexpected internal error", ex);
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex.getMessage());
    }

    /**
     *   Builds the response for the exceptions
     * @param status is the httpStatus
     * @param error is the message error
     * @param message is the exception message
     * @Return ResponseEntity<Map<String, Object>> successfully built
     **/
    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String error, String message) {
        return ResponseEntity.status(status)
                .body(Map.of(
                        "timestamp", LocalDateTime.now(),
                        "status", status.value(),
                        "error", error,
                        "message", message
                ));
    }
}
