package com.example.SpringBoot.SpringSecurity.Controllers;

import com.example.SpringBoot.SpringSecurity.DTOs.PostDTO;
import com.example.SpringBoot.SpringSecurity.Services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    PostService postService;
    @GetMapping
    public List<PostDTO>getAllPosts(){
        List<PostDTO> postDTOS = (List<PostDTO>) postService.getAllPosts();
        return postDTOS;
    }
    @GetMapping(path = "/{postId}")
    public PostDTO getPostById(@PathVariable Long postId){
        return postService.getPostById(postId);
    }

    @PostMapping
    public PostDTO createNewPost(@RequestBody PostDTO postDTO){
        return postService.createNewPost(postDTO);
    }

}
