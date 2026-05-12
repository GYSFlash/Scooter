package org.userservice.controller;

import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private static final Logger log = LogManager.getLogger(UserController.class);
    private UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Void> addUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        log.info("POST /user");
        userService.addUser(userRequestDto);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping({"/{id}"})
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") UUID id) {
        log.info("DELETE /user/{id}");
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping({"/{id}"})
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Void> updateUser(@Valid @RequestBody UserRequestDto userRequestDto,@PathVariable("id") UUID id) {
        log.info("PUT /user/{id}");
        userService.updateUser(id,userRequestDto);
        return ResponseEntity.ok().build();
    }
    @GetMapping({"/{id}"})
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public UserResponseDto getUserById(@PathVariable("id") UUID id) {
        log.info("GET /user/{id}");
        return userService.findUserById(id);
    }
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponseDto> getAllUsers() {
        log.info("GET /user");
        return userService.findAll();
    }
    @GetMapping({"/email/{email}"})
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponseDto getUserByEmail(@PathVariable("email") String email) {
        log.info("GET /user/email/{email}");
        return userService.findUserByEmail(email);
    }

}
