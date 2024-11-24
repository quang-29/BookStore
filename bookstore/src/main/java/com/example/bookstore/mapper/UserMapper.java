package com.example.bookstore.mapper;


import com.example.bookstore.dto.UserDTO;
import com.example.bookstore.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserDTO userDTO);
    UserDTO toUserDTO(User user);
}
