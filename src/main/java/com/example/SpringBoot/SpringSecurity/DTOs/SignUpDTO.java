package com.example.SpringBoot.SpringSecurity.DTOs;

import com.example.SpringBoot.SpringSecurity.Entities.emuns.Permissions;
import com.example.SpringBoot.SpringSecurity.Entities.emuns.Role;
import lombok.Data;

import java.util.Set;

@Data
public class SignUpDTO {
    private String name;
    private String email;
    private String password;
    private Set<Role> roles;
    private Set<Permissions> permissions;
}
