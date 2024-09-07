package com.example.SpringBoot.SpringSecurity.Services;

import com.example.SpringBoot.SpringSecurity.DTOs.SignUpDTO;
import com.example.SpringBoot.SpringSecurity.DTOs.UserDTO;
import com.example.SpringBoot.SpringSecurity.Entities.User;
import com.example.SpringBoot.SpringSecurity.Exceptions.ResourceNotFoundException;
import com.example.SpringBoot.SpringSecurity.Repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByEmail(username)
                .orElseThrow(()-> new ResourceNotFoundException("user with email " + username +" not found!!"));
    }
    public User getUserById(Long userId) {
        return userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with id "+ userId +
                " not found"));
    }


    public UserDTO signUp(SignUpDTO signUpDTO) {
        Optional<User> user = userRepo.findByEmail(signUpDTO.getEmail());
        if(user.isPresent()){
            throw new BadCredentialsException("User already exists with email"+ signUpDTO.getEmail());
        }
        User toBeCreatedUser = modelMapper.map(signUpDTO, User.class);
        toBeCreatedUser.setPassword(passwordEncoder.encode(toBeCreatedUser.getPassword()));
        User savedUser = userRepo.save(toBeCreatedUser);
        return modelMapper.map(savedUser, UserDTO.class);
    }
}
