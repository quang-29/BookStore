package com.example.bookplace.services.user;

import com.example.bookplace.jwt.JwtUtils;
import com.example.bookplace.models.User;
import com.example.bookplace.repositories.UserRepository;
import com.example.bookplace.request.LoginRequest;
import com.example.bookplace.response.LoginResponse;
import org.springframework.stereotype.Service;

@Service
public class IUserServide implements UserService {
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    public IUserServide(UserRepository userRepository, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
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
}
