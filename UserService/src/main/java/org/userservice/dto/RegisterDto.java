package org.userservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class RegisterDto {
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private UserRequestDto user;
}
