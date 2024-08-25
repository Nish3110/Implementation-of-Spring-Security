package com.example.SpringBoot.SpringSecurity.Services;

import com.example.SpringBoot.SpringSecurity.DTOs.PostDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {
    public List<PostDTO>getAllPosts();
    public PostDTO getPostById(Long id);
    public PostDTO createNewPost(PostDTO postDTO);
}
