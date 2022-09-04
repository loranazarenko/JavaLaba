package com.epam.onlinestore.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

/**
 * DTO class for fast serialization and transfer to the Internet
 */
@Slf4j
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class UserDto {

    @JsonProperty(access = READ_ONLY)
    public String id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String login;

    @NotBlank
    @NotNull
    private String email;

    @NotBlank
    @NotNull
    private String password;

}






