package com.example.authwithkeys.service;

import com.example.authwithkeys.dto.PostDto;
import com.example.authwithkeys.exception.PostNotFoundException;
import com.example.authwithkeys.models.Post;

import com.example.authwithkeys.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    @Autowired
    private AuthService authService;

    @Autowired
    private PostRepository postRepository;

    public void createPost(PostDto postDto){
        Post post = new Post();
        post.setContent(postDto.getContent());
        post.setTitle(postDto.getTitle());
        User username= authService.getCurrentUser().orElseThrow(()-> new IllegalArgumentException("No User logged in"));
        post.setUsername(username.getUsername());
        post.setCreatedOn(Instant.now());
        postRepository.save(post);
    }

    public List<PostDto> showAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(this::mapFromPostToDto).collect(Collectors.toList());
    }

    private  PostDto mapFromPostToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setUsername(post.getUsername());
        return postDto;
    }

    private Post mapFromDtoToPost(PostDto postDto){
        Post post = new Post();
        post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        User loggedInUser = authService.getCurrentUser().orElseThrow(()-> new IllegalArgumentException("User not found"));
        post.setCreatedOn(Instant.now());
        post.setUsername(loggedInUser.getUsername());
        post.setUpdateOn(Instant.now());
        return post;
    }

    public PostDto reaSinglePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("For if" + id));
        return mapFromPostToDto(post);
    }
}
