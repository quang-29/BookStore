package com.example.bookplace.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,String>> handleCommonException(Exception ex){
        int code = parseInt(ex.getMessage().substring(0,3));
        String message = ex.getMessage().substring(ex.getMessage().indexOf("\"")+1);
        Map<String,String> response = new HashMap<>();
        response.put("message", message);
        response.put("code", String.valueOf(code));
        return ResponseEntity.status(code)
                .body(response);
    }
}
