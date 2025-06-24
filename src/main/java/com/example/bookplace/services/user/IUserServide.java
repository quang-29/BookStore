package com.example.bookplace.services.user;

import com.example.bookplace.jwt.JwtUtils;
import com.example.bookplace.models.User;
import com.example.bookplace.repositories.UserRepository;
import com.example.bookplace.request.LoginRequest;
import com.example.bookplace.request.SignupRequest;
import com.example.bookplace.response.LoginResponse;
import com.example.bookplace.response.SignupResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class IUserServide implements UserService {
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public IUserServide(UserRepository userRepository, JwtUtils jwtUtils, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail());
        if(user.getPassword().equals(request.getPassword())){
            String token = jwtUtils.generateToken(user.getUsername());
            LoginResponse loginResponse = LoginResponse.builder()
                    .token(token)
                    .message("Log In Successfully")
                    .build();
            return loginResponse;
        }
        return LoginResponse.builder().message("Log In Failed").build();
    }

    @Override
    public SignupResponse signup(SignupRequest request) {
        User foundUser = userRepository.findByUsername(request.getUsername());
        if(foundUser != null){
            throw new RuntimeException("User already exists");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUsername(request.getUsername());
        user.setPhone(request.getPhone());
        user.setCreatedAt(new Date());
        userRepository.save(user);
        return SignupResponse.builder().message("Sign up SuccessFul").build();
    }

    @Override
    public User myProfile(String token) {
        String username = jwtUtils.getUsernameFromToken(token);
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new RuntimeException("User not found");
        }
        return user;
    }
}
