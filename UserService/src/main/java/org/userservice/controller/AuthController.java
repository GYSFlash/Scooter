package org.userservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.userservice.dto.AuthDto;
import org.userservice.dto.RegisterDto;
import org.userservice.service.AuthService;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthDto authDto) {
        try{
            return authService.login(authDto);
        }catch (Exception e){
            return null;
        }
    }
    @PostMapping("/register")
    public void register(@RequestBody RegisterDto dto) {
        try{
            authService.register(dto);
           } catch (Exception e){
            e.printStackTrace();
        }
    }
    @PutMapping("/change-pass")
    public void changePass(@RequestBody AuthDto authDto,@RequestBody String oldPassword) {
        try{
            authService.updatePassword(authDto,oldPassword);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
