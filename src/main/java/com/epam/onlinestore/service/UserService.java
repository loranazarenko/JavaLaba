package com.epam.onlinestore.service;

import com.epam.onlinestore.controller.dto.UserDto;
import com.epam.onlinestore.entity.User;
import com.epam.onlinestore.repository.impl.UserRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* Class does a business logic for users
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepositoryImpl userRepository = new UserRepositoryImpl();

    public UserDto createUser(UserDto userDto) {
        log.info("create new User with login {} ", userDto.getLogin());
        User userFromBase = userRepository.getByLogin(userDto.getLogin());
        if (userFromBase != null && userFromBase.getLogin().equals(userDto.getLogin())) {
            return null;
        }
        User user = mapUserDtoToUser(userDto);
        log.error("User: " + user);
        user = userRepository.createUser(user);
        return mapUserToUserDto(user);
    }

    public List<UserDto> getListOfUsers() {
        log.info("get all Users");
        return userRepository.getAll()
                .stream()
                .map(this::mapUserToUserDto)
                .collect(Collectors.toList());
    }

    public UserDto getByLogin(String login) {
        log.info("get User with login {}", login);
        User user = userRepository.getByLogin(login);
        return mapUserToUserDto(user);
    }

    public UserDto updateUser(String login, UserDto userDto) {
        log.info("updateUser with login {}", login);
        User user = mapUserDtoToUser(userDto);
        user = userRepository.updateUser(login, user);
        return mapUserToUserDto(user);
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
        log.info("deleteUser with login {}", login);
        userRepository.deleteUser(login);
    }
}
