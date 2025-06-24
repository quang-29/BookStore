package com.example.bookplace.services.user;

import com.example.bookplace.enums.Role;
import com.example.bookplace.jwt.JwtUtils;
import com.example.bookplace.models.User;
import com.example.bookplace.repositories.UserRepository;
import com.example.bookplace.request.LoginRequest;
import com.example.bookplace.request.SignupRequest;
import com.example.bookplace.response.LoginResponse;
import com.example.bookplace.response.SignupResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@Service
public class IUserService implements UserService {
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public IUserService(UserRepository userRepository, JwtUtils jwtUtils, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail());
        if(user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User với email không tồn tại");
        }
        if (!user.getPassword().equals(request.getPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mật khẩu đăng nhập không đúng");
        }
            String token = jwtUtils.generateToken(user.getUsername());
            return  LoginResponse.builder()
                    .token(token)
                    .message("Log In Successfully")
                    .build();
    }

    @Override
    public SignupResponse signup(SignupRequest request) {
        User foundUsername = userRepository.findByUsername(request.getUsername());
        if(foundUsername != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User with username already exists");
        }
        User foundEmail = userRepository.findByEmail(request.getEmail());
        if(foundEmail != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User with email already exists");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUsername(request.getUsername());
        user.setPhone(request.getPhone());
        user.setCreatedAt(new Date());
        user.setRole(Role.USER);
        user.setEnable(true);
        userRepository.save(user);
        return SignupResponse.builder().message("Sign up SuccessFul").build();
    }

    @Override
    public User myProfile(String username) {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found");
        }
        return user;
    }
}
