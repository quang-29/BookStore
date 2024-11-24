package com.example.bookstore.controller;


import com.example.bookstore.dto.request.IntrospectRequest;
import com.example.bookstore.dto.request.LogOutRequest;
import com.example.bookstore.dto.request.RefreshTokenRequest;
import com.example.bookstore.dto.response.DataResponse;
import com.example.bookstore.dto.response.IntrospectResponse;
import com.example.bookstore.dto.response.LogInResponse;
import com.example.bookstore.dto.response.RefreshTokenResponse;
import com.example.bookstore.service.imp.AuthenticationServiceImp;
import com.nimbusds.jose.JOSEException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private static final String SIGN_UP_SUCCESS = "Sign Up Successful";
    private static final String SIGN_UP_FAILED = "Sign Up Failed";
    private static final String LOGIN_SUCCESS = "Login Successful";
    private static final String LOGIN_FAILED = "Login Failed";

    @Autowired
    AuthenticationServiceImp authenticationServiceImp;

    @PostMapping("/singUp")
    public ResponseEntity<DataResponse> singUp(@RequestParam String username, @RequestParam String password) {

        boolean isSuccess = authenticationServiceImp.signUp(username, password);
        String message = isSuccess ? SIGN_UP_SUCCESS : SIGN_UP_FAILED;

        DataResponse dataResponse = DataResponse.builder()
                .data(isSuccess)
                .message(message)
                .build();
        return ResponseEntity.ok(dataResponse);
    }
    @GetMapping("/signIn")
    public ResponseEntity<DataResponse> signIn(@RequestParam String username, @RequestParam String password) {


        LogInResponse result = authenticationServiceImp.logIn(username, password);
        String message = result.isAuthenticated() ? LOGIN_SUCCESS : LOGIN_FAILED;
        DataResponse response = DataResponse.builder()
                .data(result)
                .message(message)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/introspectToken")
    public ResponseEntity<DataResponse> introspect(@RequestBody IntrospectRequest introspectRequest) {
        IntrospectResponse response = authenticationServiceImp.introspect(introspectRequest);
        DataResponse dataResponse = DataResponse.builder()
                .data(response)
                .build();
        return ResponseEntity.ok(dataResponse);
    }

    @PostMapping("/logOut")
    public ResponseEntity<DataResponse> refreshToken(@RequestBody LogOutRequest request) throws ParseException, JOSEException {
        authenticationServiceImp.logOut(request);
        DataResponse dataResponse = DataResponse.builder().build();
        return ResponseEntity.ok(dataResponse);
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<DataResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) throws ParseException, JOSEException {
        RefreshTokenResponse refreshTokenResponse = authenticationServiceImp.refreshToken(refreshTokenRequest);
        return ResponseEntity.ok(DataResponse.builder()
                        .data(refreshTokenResponse)
                        .build());
    }
    }

