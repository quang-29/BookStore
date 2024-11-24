package com.example.bookstore.exception;


import com.example.bookstore.dto.response.DataResponse;
import com.example.bookstore.enums.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.FileAlreadyExistsException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<DataResponse> handleAppException(AppException appException) {
        ErrorCode errorCode = appException.getErrorCode();
        DataResponse dataResponse = DataResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();

        return ResponseEntity.status(errorCode.getCode()).body(dataResponse);
    }





}
