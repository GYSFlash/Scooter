package org.userservice.mapper;

import org.mapstruct.Mapper;
import org.userservice.dto.UserRequestDto;
import org.userservice.dto.UserResponseDto;
import org.userservice.entity.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserRequestDto userRequestDto);
    UserResponseDto toUserResponseDto(User user);
    List<UserResponseDto> toUserResponseDtos(List<User> users);

}
