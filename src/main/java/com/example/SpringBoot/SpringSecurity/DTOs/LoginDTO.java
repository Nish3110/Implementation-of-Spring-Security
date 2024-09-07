package com.example.SpringBoot.SpringSecurity.DTOs;

import lombok.Data;

@Data
public class LoginDTO {
    private String email;
    private String password;
}
