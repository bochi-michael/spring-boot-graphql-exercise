package com.example.blog.service.inmemory;

import com.example.blog.exception.BadRequestException;
import com.example.blog.exception.NotFoundException;
import com.example.blog.model.User;
import com.example.blog.model.Post;
import com.example.blog.service.api.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Profile("test")
public class InMemoryPostServiceImpl implements PostService {
    private static final Logger LOG = LoggerFactory.getLogger(PostService.class);
    private Map<Long, Post> posts = new HashMap<>();

    @Autowired
    private InMemoryUserServiceImpl userService;

    @Override
    public List<Post> findAll() {
        return new ArrayList<>(posts.values());
    }

    @Override
    public List findByTitle(String postTitle) {
        return posts.values().stream()
                .filter(post -> post.getTitle().equals(postTitle))
                .collect(Collectors.toList());
    }

    @Override
    public Post findOne(Long id) {
        if (posts.containsKey(id)) {
            return posts.get(id);
        } else {
            throw new NotFoundException(Post.class);
        }
    }

    @Override
    public List<Post> findByAuthorId(Long id) {
        return posts.values().stream()
                .filter(post -> Objects.equals(post.getAuthorId(), id))
                .collect(Collectors.toList());
    }

    private Long generateId() {
        List<Long> ids = new ArrayList<>(posts.keySet());
        ids.sort(Comparator.comparingLong(a -> a));
        if (ids.size() == 0) {
            return 1L;
        } else {
            return ids.get(ids.size() - 1) + 1;
        }
    }

    @Override
    public void create(Post post) {
        if (post.getAuthorId() == null) {
            throw new BadRequestException("authorId can't be null");
        }
        if (post.getTitle() == null) {
            throw new BadRequestException("title can't be null");
        }
        User author = userService.findOne(post.getAuthorId());
        Long id = generateId();
        post.setId(id);
        posts.put(id, post);
        LOG.info("create post {}", post);
    }

    @Override
    public void delete(Long id) {
        Post post = posts.remove(id);
        LOG.info("delete post {}", id);
    }

    @Override
    public Post updatePost(Post post, Long id) {
        if (!posts.containsKey(id)) {
            throw new NotFoundException(Post.class);
        }
        posts.put(id, post);
        LOG.info("update post {}", post);
        return post;
    }
}
