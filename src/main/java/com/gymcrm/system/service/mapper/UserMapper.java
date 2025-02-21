package com.gymcrm.system.service.mapper;

import com.gymcrm.system.persistance.entity.User;
import com.gymcrm.system.service.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFistName(user.getFistName());
        userDto.setLastName(user.getLastName());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setActive(user.isActive());
        return userDto;
    }

    public User toEntity(UserDto userDto) {
        User user = new User();
        user.setFistName(userDto.getFistName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setActive(userDto.isActive());
        return user;
    }
}
