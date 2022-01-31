package com.epam.onlinestore.controller;

import com.epam.onlinestore.controller.dto.UserDto;
import com.epam.onlinestore.exception.DaoException;
import com.epam.onlinestore.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 * Restcontroller for working with users
 */
@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/")
    public List<UserDto> getAllUsers() throws SQLException, DaoException {
        return userService.getListOfUsers();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{login}")
    public UserDto getUser(@PathVariable String login) throws DaoException {
        return userService.getByLogin(login);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{email}")
    public String getUserByEmail(@PathVariable String email) throws DaoException {
        return userService.getByEmail(email);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/")
    public UserDto createUser(@RequestBody UserDto userDto) throws SQLException, DaoException {
        return userService.createUser(userDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{login}")
    public UserDto updateUser(@PathVariable String login, @RequestBody UserDto userDto) throws DaoException {
        return userService.updateUser(login, userDto);
    }

    @DeleteMapping(value = "/{login}")
    public ResponseEntity<Void> deleteUser(@PathVariable String login) {
        userService.deleteUser(login);
        return ResponseEntity.noContent().build();
    }

}