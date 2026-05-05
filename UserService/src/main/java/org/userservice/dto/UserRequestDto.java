package org.userservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
@Data
public class UserRequestDto {
    @NotNull
    private String email;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    @Min(18)
    private LocalDate dateOfBirth;
}
