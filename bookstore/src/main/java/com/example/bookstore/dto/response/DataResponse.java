package com.example.bookstore.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataResponse {
    private int code = 200 ;
    private String message;
    private Object data;

}
