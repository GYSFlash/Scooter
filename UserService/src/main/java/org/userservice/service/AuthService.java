package org.userservice.service;

import org.userservice.dto.AuthDto;
import org.userservice.dto.RegisterDto;
import org.userservice.entity.AuthorizationData;

public interface AuthService {
    void register(RegisterDto registerDto);
    String login(AuthDto authDto);
    void updatePassword(AuthDto authDto, String OldPassword);
}
