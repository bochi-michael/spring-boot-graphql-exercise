package com.example.blog.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString(exclude = "password")
public class UserDto {
    private long id;

    private String username;

    private String password;

    private List<Long> posts;
}
