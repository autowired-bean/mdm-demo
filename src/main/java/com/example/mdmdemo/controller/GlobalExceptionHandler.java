package com.example.mdmdemo.controller;


import com.example.mdmdemo.exception.DeviceAlreadyExistsException;
import com.example.mdmdemo.exception.DeviceNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<String> handleNoResourceFoundException(NoResourceFoundException ex){
        log.debug(ex.getMessage());
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(DeviceNotFoundException.class)
    public ResponseEntity<String> handleDeviceNotFoundException(DeviceNotFoundException ex){
        log.debug(ex.getMessage());
        return ResponseEntity.badRequest().body(exToJsonString(ex));
    }

    @ExceptionHandler({DeviceAlreadyExistsException.class})
    public ResponseEntity<String> handleDeviceAlreadyExistsException(DeviceAlreadyExistsException ex){
        log.debug(ex.getMessage());
        return ResponseEntity.status(HttpServletResponse.SC_CONFLICT)
                .body(exToJsonString(ex));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex){
        log.debug(ex.getMessage());
        return ResponseEntity.badRequest()
                .body(exToJsonString(ex));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleOtherDevices(Exception ex){
        log.debug(ex.getMessage());
        return ResponseEntity.notFound().build();
    }

    private static String exToJsonString(Exception e) {
        return "{ \"message\": \"%s\" }".formatted(e.getMessage());
    }

}
