package com.gymcrm.system.unit.service.mapper;

import com.gymcrm.system.persistance.entity.Trainee;
import com.gymcrm.system.persistance.entity.User;
import com.gymcrm.system.persistance.repository.UserRepository;
import com.gymcrm.system.service.dto.TraineeDto;
import com.gymcrm.system.service.dto.UserDto;
import com.gymcrm.system.service.mapper.TraineeMapper;
import com.gymcrm.system.service.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TraineeMapperTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    private TraineeMapper traineeMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        traineeMapper = new TraineeMapper(userRepository, userMapper);
    }

    @Test
    void toDto_ShouldMapTraineeToTraineeDto_WhenUserExists() {
        Trainee trainee = new Trainee(1L, 2L, LocalDate.of(1995, 8, 21), "123 Street");
        User user = new User(2L, "John", "Doe", "john.doe", "password123", true);
        UserDto userDto = new UserDto(2L, "John", "Doe", "john.doe", "password123", true);

        when(userRepository.findById(2L)).thenReturn(Optional.of(user));
        when(userMapper.toDto(user)).thenReturn(userDto);

        TraineeDto result = traineeMapper.toDto(trainee);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("123 Street", result.getAddress());
        assertNotNull(result.getUser());
        assertEquals(2L, result.getUser().getId());
    }

    @Test
    void toDto_ShouldReturnNullUserDto_WhenUserNotFound() {
        Trainee trainee = new Trainee(1L, 99L, LocalDate.of(1995, 8, 21), "456 Avenue");

        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        TraineeDto result = traineeMapper.toDto(trainee);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("456 Avenue", result.getAddress());
        assertNull(result.getUser());
    }

    @Test
    void toEntity_ShouldMapTraineeDtoToTraineeEntity() {
        UserDto userDto = new UserDto(2L, "John", "Doe", "john.doe", "password123", true);
        TraineeDto traineeDto = new TraineeDto();
        traineeDto.setId(1L);
        traineeDto.setDateOfBirth(LocalDate.of(1995, 8, 21));
        traineeDto.setAddress("123 Street");

        Trainee result = traineeMapper.toEntity(traineeDto, userDto);

        assertNotNull(result);
        assertEquals(2L, result.getUserId());
        assertEquals(LocalDate.of(1995, 8, 21), result.getDateOfBirth());
        assertEquals("123 Street", result.getAddress());
    }
}
