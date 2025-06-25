package com.example.bookplace.controllers;

import com.example.bookplace.models.User;
import com.example.bookplace.repositories.UserRepository;
import com.example.bookplace.request.auth.LoginRequest;
import com.example.bookplace.request.auth.SignupRequest;
import com.example.bookplace.response.LoginResponse;
import com.example.bookplace.response.SignupResponse;
import com.example.bookplace.services.user.IUserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final IUserService iUserService;
    private final UserRepository userRepository;

    public UserController(IUserService iUserService, UserRepository userRepository) {
        this.iUserService = iUserService;
        this.userRepository = userRepository;
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        LoginResponse response = iUserService.login(request);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> list = userRepository.findAll();
        return ResponseEntity.ok(list);
    }
    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@Valid @RequestBody SignupRequest request){
        SignupResponse response = iUserService.signup(request);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/profile")
    public ResponseEntity<User> myProfile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = iUserService.myProfile(username);
        return ResponseEntity.ok(user);
    }


}
