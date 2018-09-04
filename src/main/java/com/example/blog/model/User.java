package com.example.blog.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = "password")
public class User {

    private long id;

    private String username;

    private String password;
}
