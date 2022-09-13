package com.epam.onlinestore.util;

import com.epam.onlinestore.dto.ProductDto;
import com.epam.onlinestore.dto.UserDto;

public class UtilsForTests {
    public final static Long MOCK_ID = 2L;

    public static ProductDto createTestProductDto() {
        return ProductDto.builder()
                .id(MOCK_ID)
                .name("TESTNAME")
                .price(2.1)
                .quantity(3)
                .description("test test")
                .build();
    }

    public static UserDto createTestUserDto() {
        return UserDto.builder()
                .id(MOCK_ID)
                .firstName("TEST_FIRSTNAME")
                .lastName("TEST_LASTNAME")
                .login("TEST_LOGIN")
                .email("TEST_EMAIL")
                .password("TEST_PASSWORD")
                .build();
    }

}
