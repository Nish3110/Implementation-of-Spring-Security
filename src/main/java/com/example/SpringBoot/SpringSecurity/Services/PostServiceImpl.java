package com.example.SpringBoot.SpringSecurity.Services;

import com.example.SpringBoot.SpringSecurity.DTOs.PostDTO;
import com.example.SpringBoot.SpringSecurity.Entities.PostEntity;
import com.example.SpringBoot.SpringSecurity.Repos.PostRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    private final PostRepo postRepo;
    private final ModelMapper mapper;
    @Override
    public List<PostDTO> getAllPosts() {
        List<PostEntity> posts = postRepo.findAll();
        List<PostDTO> postDTOs = posts.stream()
                .map(post -> mapper.map(post, PostDTO.class))
                .collect(Collectors.toList());
        return postDTOs;
    }

    @Override
    public PostDTO getPostById(Long id) {
        PostDTO postDTO = mapper.map(postRepo.findById(id), PostDTO.class);
        return postDTO;
    }

    @Override
    public PostDTO createNewPost(PostDTO postDTO) {

        PostEntity toSavePost=mapper.map(postDTO, PostEntity.class);
        PostEntity savedPost = postRepo.save(toSavePost);
        PostDTO postDTO1 =mapper.map(savedPost, PostDTO.class);
        return postDTO1;
    }
}
