package com.gymcrm.system.unit.service.mapper;

import com.gymcrm.system.persistance.entity.Trainer;
import com.gymcrm.system.persistance.entity.User;
import com.gymcrm.system.persistance.repository.UserRepository;
import com.gymcrm.system.service.dto.TrainerDto;
import com.gymcrm.system.service.dto.UserDto;
import com.gymcrm.system.service.mapper.TrainerMapper;
import com.gymcrm.system.service.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TrainerMapperTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    private TrainerMapper trainerMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        trainerMapper = new TrainerMapper(userRepository, userMapper);
    }

    @Test
    void toDto_ShouldMapTrainerToTrainerDto_WhenUserExists() {
        Trainer trainer = new Trainer(1L, 2L, "Strength Training");
        User user = new User(2L, "Mike", "Johnson", "mike.trainer", "trainhard", true);
        UserDto userDto = new UserDto(2L, "Mike", "Johnson", "mike.trainer", "trainhard", true);

        when(userRepository.findById(2L)).thenReturn(Optional.of(user));
        when(userMapper.toDto(user)).thenReturn(userDto);

        TrainerDto result = trainerMapper.toDto(trainer);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Strength Training", result.getSpecialization());
        assertNotNull(result.getUser());
        assertEquals(2L, result.getUser().getId());
    }

    @Test
    void toDto_ShouldReturnNullUserDto_WhenUserNotFound() {
        Trainer trainer = new Trainer(1L, 99L, "Yoga");

        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        TrainerDto result = trainerMapper.toDto(trainer);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Yoga", result.getSpecialization());
        assertNull(result.getUser());
    }

    @Test
    void toEntity_ShouldMapTrainerDtoToTrainerEntity() {
        UserDto userDto = new UserDto(2L, "Mike", "Johnson", "mike.trainer", "trainhard", true);
        TrainerDto trainerDto = new TrainerDto();
        trainerDto.setId(1L);
        trainerDto.setSpecialization("Cardio");

        Trainer result = trainerMapper.toEntity(trainerDto, userDto);

        assertNotNull(result);
        assertEquals(2L, result.getUserId());
        assertEquals("Cardio", result.getSpecialization());
    }
}
