package com.example.bookplace.request;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

public class AuthorCreate {
    private String authorName;
    private String bio;
    private Date dob;
    private String hometown;
}
