package com.example.blog.service.api;

import com.example.blog.model.Post;

import java.util.List;

public interface PostService {
    List<Post> findAll();

    List findByTitle(String title);

    Post findOne(Long id);

    List<Post> findByAuthorId(Long id);

    void create(Post post);

    void delete(Long id);

    Post updatePost(Post post, Long id);
}
