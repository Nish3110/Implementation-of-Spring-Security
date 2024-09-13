package com.example.SpringBoot.SpringSecurity.Controllers;

import com.example.SpringBoot.SpringSecurity.DTOs.PostDTO;
import com.example.SpringBoot.SpringSecurity.Services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    PostService postService;

    @GetMapping
    @Secured("ROLE_USER")
    public ResponseEntity<List<PostDTO>> getAllPosts(){
        List<PostDTO> postDTOS = (List<PostDTO>) postService.getAllPosts();
        return ResponseEntity.ok(postDTOS);
    }
    @GetMapping(path = "/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long postId){
         PostDTO postDTO = postService.getPostById(postId);
         return ResponseEntity.ok(postDTO);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PostDTO> createNewPost(@RequestBody PostDTO postDTO){
        PostDTO newpostDTO = postService.createNewPost(postDTO);
        return ResponseEntity.ok(newpostDTO);
    }

}
