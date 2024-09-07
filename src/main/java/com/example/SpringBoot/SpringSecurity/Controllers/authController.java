package com.example.SpringBoot.SpringSecurity.Controllers;

import com.example.SpringBoot.SpringSecurity.DTOs.LoginDTO;
import com.example.SpringBoot.SpringSecurity.DTOs.LoginResponseDTO;
import com.example.SpringBoot.SpringSecurity.DTOs.SignUpDTO;
import com.example.SpringBoot.SpringSecurity.DTOs.UserDTO;
import com.example.SpringBoot.SpringSecurity.Services.AuthService;
import com.example.SpringBoot.SpringSecurity.Services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class authController {

    private final UserService userService;
    private final AuthService authService;
    @PostMapping(path = "/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignUpDTO signUpDTO){
        UserDTO userDTO = userService.signUp(signUpDTO);
        return ResponseEntity.ok(userDTO);
    }
    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO, HttpServletRequest request,
                                                  HttpServletResponse response){
        LoginResponseDTO loginResponseDTO = authService.login(loginDTO);
        Cookie cookie = new Cookie("refreshToken", loginResponseDTO.getRefreshToken());
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        response.addCookie(cookie);
        return ResponseEntity.ok(loginResponseDTO);
    }
    @PostMapping(path = "refresh")
    public ResponseEntity<LoginResponseDTO> refreshToken(HttpServletRequest request){
        String refreshToken = Arrays.stream(request.getCookies())
                .filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(cookie -> cookie.getValue())
                .orElseThrow(()-> new AuthenticationServiceException("refreshToken not found in cookie"));
        LoginResponseDTO loginResponseDTO = authService.refreshToken(refreshToken);
        return ResponseEntity.ok(loginResponseDTO);
    }


}
