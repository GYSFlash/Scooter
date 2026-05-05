package org.userservice.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.userservice.dto.UserRequestDto;
import org.userservice.dto.UserResponseDto;
import org.userservice.service.UserService;
import org.userservice.service.UserServiceImpl;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<Void> addUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        userService.addUser(userRequestDto);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteUser(@PathVariable("id") UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping({"/{id}"})
    public ResponseEntity<Void> updateUser(@Valid @RequestBody UserRequestDto userRequestDto,@PathVariable("id") UUID id) {
        userService.updateUser(id,userRequestDto);
        return ResponseEntity.ok().build();
    }
    @GetMapping({"/{id}"})
    public UserResponseDto getUserById(@PathVariable("id") UUID id) {
        return userService.findUserById(id);
    }
    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.findAll();
    }
    @GetMapping({"/{email}"})
    public UserResponseDto getUserByEmail(@PathVariable("email") String email) {
        return userService.findUserByEmail(email);
    }

}
