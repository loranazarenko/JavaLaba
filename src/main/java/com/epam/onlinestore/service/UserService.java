package com.epam.onlinestore.service;

import com.epam.onlinestore.dto.UserDto;
import com.epam.onlinestore.entity.User;
import com.epam.onlinestore.repository.UserRepository;
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

    private final UserRepository userRepository;

    public UserDto createUser(UserDto userDto) {
        log.info("create new User with login {} ", userDto.getLogin());
        User userFromBase = userRepository.getByLogin(userDto.getLogin());
        if (userFromBase != null && userFromBase.getLogin().equals(userDto.getLogin())) {
            return null;
        }
        User user = mapUserDtoToUser(userDto);
        log.info("User: " + user);
        user = userRepository.save(user);
        return mapUserToUserDto(user);
    }

    public List<UserDto> getListOfUsers() {
        log.info("get all Users");
        return userRepository.findAll()
                .stream()
                .map(this::mapUserToUserDto)
                .collect(Collectors.toList());
    }

    public List<User> getAllUsers() {
        log.info("get all Users");
        return userRepository.findAll();
    }

    public UserDto getByEmail(String email) {
        log.info("get User with email {}", email);
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            log.error("No such user!");
            return null;
        }
        return mapUserToUserDto(user);
    }

    public UserDto updateUser(Long id, UserDto userDto) {
        log.info("updateUser with Id {}", id);
        User user = mapUserDtoToUser(userDto);
        user = userRepository.save(user);
        return mapUserToUserDto(user);
    }

    public void deleteUser(String email) {
        log.info("deleteUser with email {}", email);
        userRepository.deleteUser(email);
    }

    public UserDto mapUserToUserDto(User user) {
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .login(user.getLogin())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    public User mapUserDtoToUser(UserDto userDto) {
        return User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .login(userDto.getLogin())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }

}
