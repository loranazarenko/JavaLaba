package com.epam.onlinestore.repository.impl;

import com.epam.onlinestore.entity.User;
import com.epam.onlinestore.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * a Layer class that contains methods for working with a database with a User
 */
@Slf4j
@Component
public class UserRepositoryImpl implements UserRepository {
    private final List<User> userList = new ArrayList<>();

    @Override
    public User getByLogin(String login) {
        return userList.stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User is not found!"));
    }

    @Override
    public User createUser(User user) {
        userList.add(user);
        return user;
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(userList);
    }

    @Override
    public User updateUser(String login, User user) {
        boolean isDeleted = userList.removeIf(u -> u.getLogin().equals(login));
        if (isDeleted) {
            userList.add(user);
        } else {
            throw new RuntimeException("User is not found!");
        }
        return user;
    }

    @Override
    public void deleteUser(String login) {
        userList.removeIf(user -> user.getLogin().equals(login));
    }

}
