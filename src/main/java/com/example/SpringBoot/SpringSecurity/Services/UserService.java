package com.example.SpringBoot.SpringSecurity.Services;

import com.example.SpringBoot.SpringSecurity.Exceptions.ResourceNotFoundException;
import com.example.SpringBoot.SpringSecurity.Repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByEmail(username)
                .orElseThrow(()-> new ResourceNotFoundException("user with email " + username +" not found!!"));
    }
}
