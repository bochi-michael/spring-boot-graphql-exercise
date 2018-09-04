package com.example.blog.service.inmemory;

import com.example.blog.exception.BadRequestException;
import com.example.blog.exception.NotFoundException;
import com.example.blog.model.User;
import com.example.blog.service.api.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Profile("test")
public class InMemoryUserServiceImpl implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserServiceImpl.class);
    private Map<Long, User> users = new HashMap<>();

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
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

    @Override
    public void create(User user) {
        if (user.getUsername() == null) {
            throw new BadRequestException("name can't be null");
        }
        Long id = generateId();
        user.setId(id);
        users.put(id, user);
        LOG.info("create user {}", user);
    }

    @Override
    public void delete(Long id) {
        users.remove(id);
        LOG.info("delete user {}", id);
    }

    @Override
    public User updateUser(User user, Long id) {
        if (!users.containsKey(id)) {
            throw new NotFoundException(User.class);
        }
        users.put(id, user);
        LOG.info("update user {}", user);
        return user;
    }
}
