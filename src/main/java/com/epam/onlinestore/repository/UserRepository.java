package com.epam.onlinestore.repository;

import com.epam.onlinestore.entity.User;

import java.util.List;

public interface UserRepository{

    User getByLogin(String login);

    User createUser(User user);

    List<User> getAll();

    User updateUser(String login, User user);

    void deleteUser(String login);
}
