package com.example.bookplace.services.user;

import com.example.bookplace.request.LoginRequest;
import com.example.bookplace.response.LoginResponse;

public interface UserService {
    LoginResponse login(LoginRequest request);
}
