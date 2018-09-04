package com.example.blog.service.api;

import com.example.blog.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findOne(Long id);

    void create(User user);

    void delete(Long id);

    User updateUser(User user, Long id);
}
