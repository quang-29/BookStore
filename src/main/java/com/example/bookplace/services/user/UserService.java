package com.example.bookplace.services.user;

import com.example.bookplace.models.User;
import com.example.bookplace.request.auth.LoginRequest;
import com.example.bookplace.request.auth.SignupRequest;
import com.example.bookplace.response.LoginResponse;
import com.example.bookplace.response.SignupResponse;

import java.util.List;

public interface UserService {
    LoginResponse login(LoginRequest request);
    SignupResponse signup(SignupRequest request);
    User myProfile(String username);
    List<User> getAllUsers(int pageNumber, int pageSize);
}
