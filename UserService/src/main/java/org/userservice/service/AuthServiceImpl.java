package org.userservice.service;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.userservice.dto.AuthDto;
import org.userservice.dto.RegisterDto;
import org.userservice.entity.AuthorizationData;
import org.userservice.entity.User;
import org.userservice.repository.AuthRepository;

@Service
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {

    private AuthRepository authRepository;
    private UserService userService;
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;

    public AuthServiceImpl(AuthRepository authRepository, UserService userService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.authRepository = authRepository;
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Transactional
    public void register(RegisterDto registerDto) {
        User user = userService.addUser(registerDto.getUser());
        AuthorizationData authUser = new AuthorizationData(registerDto.getUsername(), registerDto.getPassword());
        authUser.setPassword(encoder.encode(registerDto.getPassword()));
        authUser.setUser(user);
        authRepository.create(authUser);
    }
    @Transactional
    public String login(AuthDto auth) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(auth.getUsername(), auth.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(auth.getUsername());
        } else{
            return "Error";
        }
    }
    @Transactional
    public void updatePassword(AuthDto auth, String OldPassword) {
        AuthorizationData authUser = authRepository.findByUsername(auth.getUsername()).orElse(null);
        if (authUser != null) {
            if(authUser.getPassword().equals(encoder.encode(OldPassword))) {
                authUser.setPassword(encoder.encode(auth.getPassword()));
                authRepository.update(authUser);
            }
        }
    }
}

