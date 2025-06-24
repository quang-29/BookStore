package com.example.bookplace.services.user;

import com.example.bookplace.models.User;
import com.example.bookplace.request.LoginRequest;
import com.example.bookplace.request.SignupRequest;
import com.example.bookplace.response.LoginResponse;
import com.example.bookplace.response.SignupResponse;

public interface UserService {
    LoginResponse login(LoginRequest request);
    SignupResponse signup(SignupRequest request);
    User myProfile(String username);
}
