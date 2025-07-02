package com.example.bookplace.controllers;

import com.example.bookplace.models.User;
import com.example.bookplace.repositories.UserRepository;
import com.example.bookplace.request.auth.LoginRequest;
import com.example.bookplace.request.auth.SignupRequest;
import com.example.bookplace.response.LoginResponse;
import com.example.bookplace.response.SignupResponse;
import com.example.bookplace.response.PageResponse;
import com.example.bookplace.services.user.IUserService;
import com.example.bookplace.services.user.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final IUserService iUserService;
    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(IUserService iUserService, UserRepository userRepository, UserService userService) {
        this.iUserService = iUserService;
        this.userRepository = userRepository;
        this.userService = userService;
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        LoginResponse response = iUserService.login(request);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/all")
    public ResponseEntity<Page<User>> getAllUsers(Pageable pageable) {
        Page<User> pageResponse = userService.getAllUsers(pageable);
        return ResponseEntity.ok(pageResponse);
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

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        User user = userService.findUserById(id);
        return ResponseEntity.ok(user);
    }



}
