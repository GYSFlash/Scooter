package org.userservice.service;


import org.userservice.dto.AuthDto;
import org.userservice.dto.UserRequestDto;
import org.userservice.dto.UserResponseDto;
import org.userservice.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User addUser(UserRequestDto user);
    void deleteUser(UUID id);
    void updateUser(UUID id, UserRequestDto user);
    UserResponseDto findUserByEmail(String email);
    UserResponseDto findUserById(UUID id);
    List<UserResponseDto> findAll();

}
