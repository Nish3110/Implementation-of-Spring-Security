package com.example.SpringBoot.SpringSecurity.DTOs;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String email;
}
