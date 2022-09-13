package com.epam.onlinestore.controller;

import com.epam.onlinestore.dto.UserDto;
import com.epam.onlinestore.service.UserService;
import com.epam.onlinestore.util.UtilsForTests;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Testing of the User rest controller for working with users
 */

@Slf4j
@WebMvcTest(value = UserController.class)
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void getAllUsers() throws Exception {
        UserDto userDto = UtilsForTests.createTestUserDto();
        when(userService.getListOfUsers()).thenReturn(Collections.singletonList(userDto));

        mockMvc.perform(get("/user/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].firstName").value(userDto.getFirstName()));

    }

    @Test
    void getByEmail() throws Exception {

        UserDto userDto = UtilsForTests.createTestUserDto();
        when(userService.getByEmail("TEST_EMAIL")).thenReturn(userDto);

        mockMvc.perform(get("/user/{email}", "TEST_EMAIL"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("firstName").value(userDto.getFirstName()));
    }

    @Test
    void createUser() throws Exception {
        UserDto userDto = UtilsForTests.createTestUserDto();
        when(userService.createUser(any(UserDto.class))).thenReturn(userDto);

        mockMvc.perform(post("/user/")
                        .content(objectMapper.writeValueAsString(userDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.login").value("TEST_LOGIN"));
    }

    @Test
    void updateUser() throws Exception {
        UserDto userDto = UtilsForTests.createTestUserDto();

        when(userService.updateUser(eq(UtilsForTests.MOCK_ID), any())).thenReturn(userDto);

        mockMvc.perform(
                        put("/user/{id}", UtilsForTests.MOCK_ID)
                                .content(objectMapper.writeValueAsString(userDto))
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("email").value(userDto.getEmail()));
    }

  @Test
  void deleteUser() throws Exception {
      willDoNothing().given(userService).deleteUser("TEST_EMAIL");

      mockMvc.perform(get("/user/{email}", "TEST_EMAIL"))
              .andDo(print())
              .andExpect(status().isOk());
    }
}