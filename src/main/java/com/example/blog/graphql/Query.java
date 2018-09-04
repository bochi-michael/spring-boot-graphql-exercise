package com.example.blog.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.blog.model.Post;
import com.example.blog.model.User;
import com.example.blog.service.api.PostService;
import com.example.blog.service.api.UserService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {

    private final PostService postService;
    private final UserService userService;

    public Query(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    public List<Post> recentPosts(int count, int offset) {
        return postService.findAll();
    }

    public List<User> allUsers(int count, int offset) {
        return userService.findAll();
    }
}
