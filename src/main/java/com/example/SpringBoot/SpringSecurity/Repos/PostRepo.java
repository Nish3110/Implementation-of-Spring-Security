package com.example.SpringBoot.SpringSecurity.Repos;

import com.example.SpringBoot.SpringSecurity.Entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends JpaRepository<PostEntity,Long> {
}
