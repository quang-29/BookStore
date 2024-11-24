package com.example.bookstore.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorCode {

    USER_NOT_FOUND(700, "User not found", HttpStatus.NOT_FOUND),
    INVALID_REQUEST(701, "Invalid request", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR(702, "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHENTICATED(703,"Unauthenticated",HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(704,"Unauthorized",HttpStatus.FORBIDDEN),
    STORE_NOT_FOUND(705,"Store not found",HttpStatus.BAD_REQUEST),
    BOOK_NOT_FOUND(706,"Books not found",HttpStatus.BAD_REQUEST),
    AUTHOR_NOT_FOUND(707,"Authors not found",HttpStatus.BAD_REQUEST),
    ORDER_NOT_FOUND(708,"Order not found",HttpStatus.BAD_REQUEST);

    private int code;
    private String message;
    private HttpStatus httpStatus;

}
