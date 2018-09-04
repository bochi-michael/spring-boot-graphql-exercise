package com.example.blog.dto;

import lombok.Data;

@Data
public class PostDto {
    private Long id;

    private String title;

    private Long authorId;

    private String category;

    private String text;
}
