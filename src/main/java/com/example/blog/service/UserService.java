package com.example.blog.service;

import com.example.blog.exception.BadRequestException;
import com.example.blog.exception.NotFoundException;
import com.example.blog.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    private Map<Long, User> users = new HashMap<>();

    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    public User findOne(Long id) {
        if (users.containsKey(id)) {
            return users.get(id);
        } else {
            throw new NotFoundException(User.class);
        }
    }

    private Long generateId() {
        List<Long> ids = new ArrayList<>(users.keySet());
        ids.sort(Comparator.comparingLong(a -> a));
        if (ids.size() == 0) {
            return 1L;
        } else {
            return ids.get(ids.size() - 1) + 1;
        }
    }

    public User create(User user) {
        if (user.getName() == null) {
            throw new BadRequestException("name can't be null");
        }
        Long id = generateId();
        user.setId(id);
        users.put(id, user);
        LOG.info("create user {}", user);
        return user;
    }

    public void delete(Long id) {
        users.remove(id);
        LOG.info("delete user {}", id);
    }

    public User updateUser(User user, Long id) {
        if (!users.containsKey(id)) {
            throw new NotFoundException(User.class);
        }
        users.put(id, user);
        LOG.info("update user {}", user);
        return user;
    }
}
