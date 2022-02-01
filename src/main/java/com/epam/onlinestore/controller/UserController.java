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
    public String getAllUsers() throws SQLException, DaoException {
       // return userService.getListOfUsers();
        return " list of users";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{login}")
    public String getUser(@PathVariable String login) throws DaoException {
       // return userService.getByLogin(login);
        return " Return the user";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{email}")
    public String getUserByEmail(@PathVariable String email) throws DaoException {
        return userService.getByEmail(email);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/")
    public String createUser(@RequestBody UserDto userDto) throws SQLException, DaoException {
       // return userService.createUser(userDto);
        return "Create user";
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{login}")
    public String updateUser(@PathVariable String login, @RequestBody UserDto userDto) throws DaoException {
       // return userService.updateUser(login, userDto);
        return "edit user";
    }

    @DeleteMapping(value = "/{login}")
    public ResponseEntity<Void> deleteUser(@PathVariable String login) {
      //  userService.deleteUser(login);
        return ResponseEntity.noContent().build();
    }

}