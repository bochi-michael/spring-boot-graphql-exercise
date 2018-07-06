package com.example.blog.controller;

import com.example.blog.dto.PostDto;
import com.example.blog.model.Post;
import com.example.blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private static final Logger LOG = LoggerFactory.getLogger(PostController.class);
    private final PostService postService;
    private final ModelMapper modelMapper;

    @Autowired
    public PostController(PostService postService, ModelMapper modelMapper) {
        this.postService = postService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<PostDto> findAll() {
        return postService.findAll().stream()
                .map(this::poToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/title/{postTitle}")
    public List findByTitle(@PathVariable String postTitle) {
        return postService.findByTitle(postTitle);
    }

    @GetMapping("/{id}")
    public PostDto findOne(@PathVariable Long id) {
        return poToDto(postService.findOne(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto create(@RequestBody PostDto post) {
        return poToDto(
                postService.create(dtoToPo(post)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        postService.delete(id);
    }

    @PutMapping("/{id}")
    public PostDto updatePost(@RequestBody PostDto post, @PathVariable Long id) {
        return poToDto(
                postService.updatePost(dtoToPo(post), id));
    }

    private Post dtoToPo(PostDto dto) {
        return modelMapper.map(dto, Post.class);
    }

    private PostDto poToDto(Post po) {
        return modelMapper.map(po, PostDto.class);
    }
}
