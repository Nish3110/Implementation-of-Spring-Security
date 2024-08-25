package com.example.SpringBoot.SpringSecurity.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin(Customizer.withDefaults())
                .authorizeHttpRequests(auth-> auth
                        .requestMatchers("/posts").permitAll()
                        .requestMatchers("/posts/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                    .csrf(csrfConfig -> csrfConfig.disable())
                    .sessionManagement(sessionConfig-> sessionConfig
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return httpSecurity.build();
    }
}
