package com.example.bookplace.services.user;

import com.example.bookplace.models.User;
import com.example.bookplace.request.auth.LoginRequest;
import com.example.bookplace.request.auth.SignupRequest;
import com.example.bookplace.response.LoginResponse;
import com.example.bookplace.response.SignupResponse;
import com.example.bookplace.response.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    LoginResponse login(LoginRequest request);
    SignupResponse signup(SignupRequest request);
    User myProfile(String username);
    Page<User> getAllUsers(Pageable pageable);

    User findUserById(Long id);
}
