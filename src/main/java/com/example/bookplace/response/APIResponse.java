package com.example.bookplace.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class APIResponse {
    private int code;
    private String message;
    private Object data;
}
