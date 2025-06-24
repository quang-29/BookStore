package com.example.bookplace.controllers;

import com.example.bookplace.models.User;
import com.example.bookplace.repositories.UserRepository;
import com.example.bookplace.request.LoginRequest;
import com.example.bookplace.request.SignupRequest;
import com.example.bookplace.response.LoginResponse;
import com.example.bookplace.services.user.IUserServide;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final IUserServide iUserServide;
    private final UserRepository userRepository;

    public UserController(IUserServide iUserServide, UserRepository userRepository) {
        this.iUserServide = iUserServide;
        this.userRepository = userRepository;
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        LoginResponse response = iUserServide.login(request);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> list = userRepository.findAll();
        return ResponseEntity.ok(list);
    }
}
