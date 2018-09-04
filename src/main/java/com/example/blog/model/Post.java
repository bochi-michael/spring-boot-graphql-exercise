package com.example.blog.model;

import lombok.Data;

@Data
public class Post {

    private Long id;

    private String title;

    private Long authorId;

    private String category;

    private String text;
}
