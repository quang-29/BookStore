package com.example.bookstore.controller;


import com.example.bookstore.dto.UserDTO;
import com.example.bookstore.dto.response.DataResponse;
import com.example.bookstore.entity.User;
import com.example.bookstore.service.UserService;
import com.example.bookstore.service.imp.UserServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImp userServiceImp;


    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("username: {}", authentication.getName());
        log.info("role: {}", authentication.getAuthorities());
        List<UserDTO> userDTOS = userServiceImp.getAllUsers();
        return ResponseEntity.ok(userDTOS);
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<DataResponse> getUserById(@PathVariable("id") int id) {
        UserDTO userDTO = userServiceImp.getUserById(id);
        DataResponse dataResponse = DataResponse.builder()
                .data(userDTO)
                .build();
        return ResponseEntity.ok(dataResponse);
    }

    @GetMapping("/myProfile")
    public ResponseEntity<DataResponse> getMyProfile() {
        return ResponseEntity.ok(userServiceImp.getMyInfo());

    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<DataResponse> updateUser(@PathVariable("id") int id, @RequestBody User User) {
            boolean isUpdate = userServiceImp.updateUser(id, User);
            DataResponse dataResponse = DataResponse.builder()
                    .message(isUpdate ? "Update user successfully":"Update user failed")
                    .data(isUpdate).build();

            return ResponseEntity.ok(dataResponse);
    }
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<DataResponse> deleteUser(@PathVariable("id") int id) {
        boolean isDeleted = userServiceImp.deleteUser(id);
        DataResponse dataResponse = DataResponse.builder()
                .data(isDeleted)
                .message(isDeleted ? "Delete user successfully":"Delete user failed")
                .build();
        return ResponseEntity.ok(dataResponse);
    }
}
