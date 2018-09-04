package com.example.blog.controller;

import com.example.blog.dto.UserDto;
import com.example.blog.model.Post;
import com.example.blog.model.User;
import com.example.blog.service.api.PostService;
import com.example.blog.service.api.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final PostService postService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService,
                          PostService postService,
                          ModelMapper modelMapper) {
        this.userService = userService;
        this.postService = postService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<UserDto> findAll() {
        return userService.findAll().stream()
                .map(this::poToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserDto findOne(@PathVariable Long id) {
        return poToDto(userService.findOne(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@RequestBody UserDto user) {
        User po = dtoToPo(user);
        userService.create(po);
        return poToDto(po);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@RequestBody UserDto user, @PathVariable Long id) {
        return poToDto(userService.updateUser(dtoToPo(user), id));
    }

    private User dtoToPo(UserDto dto) {
        return modelMapper.map(dto, User.class);
    }

    private UserDto poToDto(User po) {
        UserDto dto = modelMapper.typeMap(User.class, UserDto.class)
                .addMappings(mapper -> mapper.skip(UserDto::setPassword))
                .map(po);
        dto.setPosts(postService.findByAuthorId(po.getId()).stream()
                .map(Post::getId)
                .collect(Collectors.toList()));
        return dto;
    }
}
