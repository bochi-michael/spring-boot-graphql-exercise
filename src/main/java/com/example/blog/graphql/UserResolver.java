package com.example.blog.graphql;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.blog.model.Post;
import com.example.blog.model.User;
import com.example.blog.service.api.PostService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserResolver implements GraphQLResolver<User> {
    private final PostService postService;

    public UserResolver(PostService postService) {
        this.postService = postService;
    }

    public List<Post> posts(User user) {
        return postService.findByAuthorId(user.getId());
    }
}
