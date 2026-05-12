package org.userservice.controller;

import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.userservice.dto.AuthDto;
import org.userservice.dto.RegisterDto;
import org.userservice.service.AuthService;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private static final Logger log = LogManager.getLogger(AuthController.class);
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody AuthDto authDto) {
        try{
            log.info("POST /auth/login");
            return authService.login(authDto);
        }catch (Exception e){
            log.error("Ошибка авторизации");
            return null;
        }
    }
    @PostMapping("/register")
    public void register(@Valid @RequestBody RegisterDto dto) {
        try{
            log.info("POST /auth/register");
            authService.register(dto);
           } catch (Exception e){
               log.error("Ошибка регистрации");
            e.printStackTrace();
        }
    }
    @PutMapping("/change-pass/{oldPassword}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public void changePass(@Valid @RequestBody AuthDto authDto, @PathVariable String oldPassword) {
        try{
            log.info("PUT /auth/change-pass");
            authService.updatePassword(authDto,oldPassword);
        } catch (Exception e){
            log.error("Ошибка смены пароля");
            e.printStackTrace();
        }
    }
}
