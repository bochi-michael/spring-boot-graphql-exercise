package com.example.blog.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.blog.model.Post;
import org.springframework.stereotype.Component;

@Component
public class Mutation implements GraphQLMutationResolver {
    public Post writePost(String title, String text, String category) {
        return null;
    }
}
