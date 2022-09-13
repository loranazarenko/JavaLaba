package com.epam.onlinestore.service;

import com.epam.onlinestore.dto.UserDto;
import com.epam.onlinestore.entity.User;
import com.epam.onlinestore.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Class tests a business logic for users
 */
@Slf4j
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private final static String MOCK_EMAIL = "EMAIL";

    @Test
    public void createUserTest() {

        //given
        when(userRepository.findAll()).thenReturn(Collections.singletonList(User.builder()
                .firstName("Ivan")
                .lastName("Sergeev")
                .login("IvanDude")
                .email(MOCK_EMAIL)
                .password("123")
                .build()));

        //when
        List<UserDto> userDtos = userService.getListOfUsers();
        System.out.println(userDtos);
        //then
        assertThat(userDtos, hasSize(1));
    }

    @Test
    public void getListOfUsersTest() {
        //given
        List<User> expectedData = new ArrayList<>();
        User user = User.builder()
                .firstName("Ivan")
                .lastName("Sergeev")
                .login("IvanDude")
                .email(MOCK_EMAIL)
                .password("123")
                .build();
        expectedData.add(user);
        when(userRepository.findAll())
                .thenReturn(expectedData);

        //when
        List<User> actualUsers = userService.getAllUsers();

        //then
        assertEquals(expectedData.get(0), actualUsers.get(0));//given
    }

    @Test
    public void getByEmailTest() {
        //given
        User expectedUser = User.builder().email(MOCK_EMAIL).build();
        when(userRepository.findByEmail(MOCK_EMAIL)).thenReturn(java.util.Optional.ofNullable(expectedUser));

        //when
        UserDto actualUser = userService.getByEmail(MOCK_EMAIL);

        //then
        assert expectedUser != null;
        assertEquals(expectedUser.getEmail(), actualUser.getEmail());
    }

    @Test
    public void updateUserTest() {

        //given
        when(userRepository.findAll()).thenReturn(Collections.singletonList(User.builder().build()));

        //when
        List<UserDto> users = userService.getListOfUsers();

        //then
        assertThat(users, hasSize(1));
    }

    @Test
    public void deleteUserTest() {

        //given
        doNothing().when(userRepository).deleteUser(MOCK_EMAIL);

        //when
        userService.deleteUser(MOCK_EMAIL);

        //then
        verify(userRepository, times(1)).deleteUser(MOCK_EMAIL);
    }

    @Test
    void deleteUserWithExceptionTest() {
        doThrow(RuntimeException.class).when(userRepository).deleteUser(any());

        assertThrows(RuntimeException.class,
                () -> userService.deleteUser(MOCK_EMAIL));
    }
}
