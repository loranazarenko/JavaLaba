package com.epam.onlinestore.service;

import com.epam.onlinestore.controller.dto.UserDto;
import com.epam.onlinestore.dao.UserDAO;
import com.epam.onlinestore.dao.impl.UserDAOImpl;
import com.epam.onlinestore.entity.User;
import com.epam.onlinestore.exception.DaoException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
* Class does a business logic for users
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDAO userDAO = new UserDAOImpl();

    public UserDto createUser(UserDto userDto) throws DaoException, SQLException {

        User userFromBase = userDAO.getByLogin(userDto.getLogin());
        if (userFromBase != null && userFromBase.getLogin().equals(userDto.getLogin())) {
            return null;
        }

        User user = new User();
        user.setPassword(userDto.getPassword());
        user.setLogin(escapeForInjection(userDto.getLogin()));
        log.error("User: " + user);
        user = userDAO.createUser(user.getLogin(), user.getPassword());
        return mapUserToUserDto(user);
    }

    public User insertUserToBase(String login, String password, String name, String email) throws DaoException, SQLException {

        User userFromBase = userDAO.getByLogin(login);
        if (userFromBase != null && userFromBase.getLogin().equals(login)) {
            return null;
        }

        User user = new User();
        user.setPassword(password);
        user.setLogin(escapeForInjection(login));
        log.error("User: " + user);

        user = userDAO.createUser(user.getLogin(), user.getPassword());
        log.debug("User info added to DB: " + user.getLogin());

        return user;
    }

    public List<UserDto> getListOfUsers() throws DaoException, SQLException { //User
        return userDAO.getAll()
                .stream()
                .map(this::mapUserToUserDto)
                .collect(Collectors.toList());
    }

    public UserDto getByLogin(String login) throws DaoException {
        User user = userDAO.getByLogin(login);
        return mapUserToUserDto(user);
    }

    public String getByEmail(String email) throws DaoException {
        return "email";
    }

    public void updateUser(User user) throws DaoException {
        userDAO.updateUser(user);
    }

    public UserDto updateUser(String login, UserDto userDto) throws DaoException {
        log.info("updateUser with login {}", login);
        User user = mapUserDtoToUser(userDto);
        user = userDAO.updateUser(login, user);
        return mapUserToUserDto(user);
    }

    static String escapeForInjection(String param) {
        return param.replace("!", "!!").
                replace("<", "!<").
                replace(">", "!").
                replace("%", "!%").
                replace("_", "!_").
                replace("[", "![");
    }

    private UserDto mapUserToUserDto(User user) {
        return UserDto.builder()
                .login(user.getLogin())
                .email(user.getEmail())
                .build();
    }

    private User mapUserDtoToUser(UserDto userDto) {
        return User.builder()
                .login(userDto.getLogin())
                .email(userDto.getEmail())
                .build();
    }

    public void deleteUser(String login) {
    }
}
