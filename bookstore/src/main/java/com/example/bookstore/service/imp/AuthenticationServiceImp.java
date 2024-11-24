package com.example.bookstore.service.imp;

import com.example.bookstore.dto.request.IntrospectRequest;
import com.example.bookstore.dto.request.LogOutRequest;
import com.example.bookstore.dto.request.RefreshTokenRequest;
import com.example.bookstore.dto.response.IntrospectResponse;
import com.example.bookstore.dto.response.LogInResponse;
import com.example.bookstore.dto.response.RefreshTokenResponse;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface AuthenticationServiceImp {
    boolean signUp(String username, String password);
    LogInResponse logIn(String username, String password);
    IntrospectResponse introspect(IntrospectRequest request) ;
    void logOut(LogOutRequest request) throws ParseException, JOSEException;
    RefreshTokenResponse refreshToken(RefreshTokenRequest request) throws ParseException, JOSEException;


}
