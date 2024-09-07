package com.example.SpringBoot.SpringSecurity.Services;

import com.example.SpringBoot.SpringSecurity.DTOs.LoginDTO;
import com.example.SpringBoot.SpringSecurity.DTOs.LoginResponseDTO;
import com.example.SpringBoot.SpringSecurity.Entities.User;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final UserService userService;
    public LoginResponseDTO login(LoginDTO loginDTO ){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword()));
        User user = (User) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        return new LoginResponseDTO(user.getId(),accessToken,refreshToken);
    }
    public LoginResponseDTO refreshToken(String refreshToken){
        Long userId =  jwtService.getUserIdFromToken(refreshToken);
        User user = userService.getUserById(userId);
        String accessToken = jwtService.generateAccessToken(user);
        return new LoginResponseDTO(user.getId(),accessToken,refreshToken);
    }
}
