package com.example.blog.graphql;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.blog.model.Post;
import com.example.blog.model.User;
import com.example.blog.service.api.UserService;
import org.springframework.stereotype.Component;

@Component
public class PostResolver implements GraphQLResolver<Post> {
    private final UserService userService;

    public PostResolver(UserService userService) {
        this.userService = userService;
    }

    public User author(Post post) {
        return userService.findOne(post.getId());
    }
}
