package com.example.SpringBoot.SpringSecurity.Repos;

import com.example.SpringBoot.SpringSecurity.Entities.User;
import com.example.SpringBoot.SpringSecurity.Entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findByUser(User user);

    Optional<Session> findByRefreshToken(String refreshToken);
}
