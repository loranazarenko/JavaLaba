package com.epam.onlinestore.dao;

import com.epam.onlinestore.entity.User;
import com.epam.onlinestore.exception.DaoException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserDAO {

    User createUser(String login, String password) throws DaoException, SQLException;

    List<User> getAll() throws DaoException, SQLException;

    User getByLogin(String login) throws DaoException;

    User getById(long id) throws DaoException;

    Optional<User> updateUser(User account) throws DaoException;

    User updateUser(String login, User user);
}
