package com.example.SpringBoot.SpringSecurity.DTOs;

import lombok.Data;

@Data
public class SignUpDTO {
    private String name;
    private String email;
    private String password;
}
