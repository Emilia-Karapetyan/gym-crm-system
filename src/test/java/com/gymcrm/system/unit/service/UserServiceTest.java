package com.gymcrm.system.unit.service;

import com.gymcrm.system.persistance.entity.User;
import com.gymcrm.system.persistance.repository.UserRepository;
import com.gymcrm.system.service.UserService;
import com.gymcrm.system.service.dto.UserDto;
import com.gymcrm.system.service.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;

    @Test
    public void test_shouldCreateUser() {
        UserDto userDto = new UserDto("John", "Smith", true);

        when(userMapper.toEntity(userDto)).thenReturn(new User());
        when(userRepository.save(any(User.class))).thenReturn(new User());
        when(userMapper.toDto(any(User.class))).thenReturn(userDto);

        UserDto result = userService.create(userDto);

        assertNotNull(result);
        assertEquals(true, result.isActive());
        assertEquals("John", result.getFistName());
        assertEquals("Smith", result.getLastName());
    }

    @Test
    public void test_shouldGetAllUsers() {
        User user1 = new User(1L, "John", "Smith", "John.Smith", "password", true);
        User user2 = new User(2L, "Emily", "Brown", "Emily.Brown", "password", true);

        UserDto userDto1 = new UserDto("John", "Smith", true);
        UserDto userDto2 = new UserDto("Emily", "Brown", true);

        when(userRepository.findAll()).thenReturn(List.of(user1, user2));
        when(userMapper.toDto(user1)).thenReturn(userDto1);
        when(userMapper.toDto(user2)).thenReturn(userDto2);

        List<UserDto> result = userService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getFistName());
        assertEquals("Emily", result.get(1).getFistName());
        assertEquals("Smith", result.get(0).getLastName());
        assertEquals("Brown", result.get(1).getLastName());
    }

    @Test
    public void test_shouldGetById() {
        User user = new User(1L, "John", "Smith", "John.Smith", "password", true);
        UserDto userDto = new UserDto("John", "Smith", true);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userMapper.toDto(user)).thenReturn(userDto);

        Optional<UserDto> result = userService.getById(1L);

        assertTrue(result.isPresent());
        assertEquals("John", result.get().getFistName());
        assertEquals("Smith", result.get().getLastName());
    }

    @Test
    public void test_shouldNotGetById_whenNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<UserDto> result = userService.getById(1L);

        assertTrue(result.isEmpty());
    }
}
