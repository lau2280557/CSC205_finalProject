package com.example.aggregator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

public class ControllerAdvisor {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(body);
    }
}
