package com.example.bookstore.service.imp;


import com.example.bookstore.dto.UserDTO;
import com.example.bookstore.dto.response.DataResponse;
import com.example.bookstore.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserServiceImp {

    List<UserDTO> getAllUsers();
    UserDTO getUserById(int id);
    boolean deleteUserById(int id);
    DataResponse getMyInfo();
    boolean updateUser(int id, User User);
    boolean deleteUser(int id);

}
