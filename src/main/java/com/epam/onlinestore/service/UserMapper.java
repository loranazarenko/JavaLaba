package com.epam.onlinestore.service;

import com.epam.onlinestore.dto.UserDto;
import com.epam.onlinestore.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
* Declaring an instruction for mapstruct
*/
@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDto mapUserDto(User account);
    User mapUser(UserDto userDto);

}


