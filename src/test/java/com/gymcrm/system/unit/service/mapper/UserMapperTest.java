package com.gymcrm.system.unit.service.mapper;

import com.gymcrm.system.persistance.entity.User;
import com.gymcrm.system.service.dto.UserDto;
import com.gymcrm.system.service.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        userMapper = new UserMapper();
    }

    @Test
    void toDto_ShouldMapUserToUserDto() {
        User user = new User();
        user.setId(1L);
        user.setFistName("John");
        user.setLastName("Doe");
        user.setUsername("john.doe");
        user.setPassword("password123");
        user.setActive(true);

        UserDto result = userMapper.toDto(user);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John", result.getFistName());
        assertEquals("Doe", result.getLastName());
        assertEquals("john.doe", result.getUsername());
        assertEquals("password123", result.getPassword());
        assertTrue(result.isActive());
    }

    @Test
    void toEntity_ShouldMapUserDtoToUser() {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setFistName("Jane");
        userDto.setLastName("Smith");
        userDto.setUsername("jane.smith");
        userDto.setPassword("securepass");
        userDto.setActive(false);

        User result = userMapper.toEntity(userDto);

        assertNotNull(result);
        assertEquals("Jane", result.getFistName());
        assertEquals("Smith", result.getLastName());
        assertEquals("jane.smith", result.getUsername());
        assertEquals("securepass", result.getPassword());
        assertFalse(result.isActive());
    }
}
