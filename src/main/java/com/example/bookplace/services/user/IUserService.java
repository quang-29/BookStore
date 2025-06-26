package com.example.bookplace.services.user;

import com.example.bookplace.enums.Role;
import com.example.bookplace.jwt.JwtUtils;
import com.example.bookplace.models.User;
import com.example.bookplace.repositories.UserRepository;
import com.example.bookplace.request.auth.LoginRequest;
import com.example.bookplace.request.auth.SignupRequest;
import com.example.bookplace.response.LoginResponse;
import com.example.bookplace.response.SignupResponse;
import com.example.bookplace.response.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.Optional;

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
        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
        if(optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User với email không tồn tại");
        }
        User user = optionalUser.get();
        if (!user.getPassword().equals(request.getPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mật khẩu đăng nhập không đúng");
        }
            String token = jwtUtils.generateToken(user.getUsername());
            return  LoginResponse.builder()
                    .token(token)
                    .message("Log In Successfully")
                    .build();
    }

    @Transactional
    @Override
    public SignupResponse signup(SignupRequest request) {
        Optional<User> optionalUsername = userRepository.findByUsername(request.getUsername());
        if(optionalUsername.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User with username already exists");
        }
        Optional<User> optionalUserEmail = userRepository.findByEmail(request.getEmail());
        if(optionalUserEmail.isPresent()){
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
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if(optionalUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found");
        }
        return optionalUser.get();
    }
    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users;
    }

    @Override
    public User findUserById(Long id) {
        Optional<User> optionalUser = userRepository.getUserById(id);
        if(optionalUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found");
        }
        return optionalUser.get();
    }
}
