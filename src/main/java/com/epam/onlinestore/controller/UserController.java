package com.epam.onlinestore.controller;

import com.epam.onlinestore.dto.UserDto;
import com.epam.onlinestore.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest controller for working with users
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
    public List<UserDto> getAllUsers() {
        return userService.getListOfUsers();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{email}")
    public UserDto getByEmail(@PathVariable String email) {
        return userService.getByEmail(email);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/")
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return userService.updateUser(id, userDto);
    }

    @DeleteMapping(value = "/{email}")
    public ResponseEntity<Void> deleteUser(@PathVariable String email) {
        userService.deleteUser(email);
        return ResponseEntity.noContent().build();
    }
}