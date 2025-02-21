package com.gymcrm.system.unit.service;

import com.gymcrm.system.persistance.entity.Trainee;
import com.gymcrm.system.persistance.repository.TraineeRepository;
import com.gymcrm.system.service.TraineeService;
import com.gymcrm.system.service.UserService;
import com.gymcrm.system.service.dto.TraineeDto;
import com.gymcrm.system.service.dto.UserDto;
import com.gymcrm.system.service.exception.EntityNotFoundException;
import com.gymcrm.system.service.mapper.TraineeMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TraineeServiceTest {

    @InjectMocks
    private TraineeService traineeService;
    @Mock
    private TraineeRepository traineeRepository;
    @Mock
    private UserService userService;
    @Mock
    private TraineeMapper traineeMapper;

    @Test
    public void test_shouldCreateValidTrainee() {
        UserDto userDto = new UserDto("John", "Smith", true);
        TraineeDto newTrainee = new TraineeDto(userDto, LocalDate.of(2000, 1, 15), "New York");

        when(userService.create(any(UserDto.class))).thenReturn(userDto);
        when(traineeMapper.toEntity(newTrainee, userDto)).thenReturn(new Trainee());
        when(traineeRepository.save(any(Trainee.class))).thenReturn(new Trainee());
        when(traineeMapper.toDto(any(Trainee.class))).thenReturn(newTrainee);

        TraineeDto result = traineeService.create(newTrainee);

        assertNotNull(result);
        assertEquals("New York", result.getAddress());
        assertEquals(LocalDate.of(2000, 1, 15), result.getDateOfBirth());
        assertEquals("John", result.getUser().getFistName());
        assertEquals("Smith", result.getUser().getLastName());
    }

    @Test
    public void test_shouldUpdateTrainee() {
        Trainee trainee = new Trainee(1L, 1L, LocalDate.of(2000, 1, 15), "New York");
        UserDto userDto = new UserDto("John", "Smith", true);
        TraineeDto newTrainee = new TraineeDto(userDto, LocalDate.of(2000, 1, 15), "Los Angeles");
        newTrainee.setId(1L);

        when(traineeRepository.findById(trainee.getId())).thenReturn(Optional.of(trainee));
        when(traineeRepository.save(any(Trainee.class))).thenReturn(trainee);
        when(traineeMapper.toDto(any(Trainee.class))).thenReturn(newTrainee);

        TraineeDto result = traineeService.update(newTrainee.getId(), newTrainee);

        assertNotNull(result);
        assertEquals("Los Angeles", result.getAddress());
        assertEquals(LocalDate.of(2000, 1, 15), result.getDateOfBirth());
    }

    @Test
    public void test_shouldNotUpdateTrainee_whenNotFound() {
        UserDto userDto = new UserDto("John", "Smith", true);
        TraineeDto traineeDto = new TraineeDto(userDto, LocalDate.of(2000, 1, 15), "Los Angeles");

        when(traineeRepository.findById(1L)).thenReturn(Optional.empty());

        EntityNotFoundException result = assertThrows(EntityNotFoundException.class, () -> {
            traineeService.update(1L, traineeDto);
        });

        assertEquals("Trainee not found", result.getMessage());
    }

    @Test
    public void test_shouldGetAllTrainees() {
        Trainee trainee1 = new Trainee(1L, 1L, LocalDate.of(2000, 1, 15), "New York");
        Trainee trainee2 = new Trainee(2L, 1L, LocalDate.of(2000, 5, 1), "Los Angeles");
        UserDto userDto = new UserDto("John", "Smith", true);
        TraineeDto traineeDto1 = new TraineeDto(userDto, LocalDate.of(2000, 1, 15), "New York");
        TraineeDto traineeDto2 = new TraineeDto(userDto, LocalDate.of(2000, 5, 1), "Los Angeles");

        when(traineeRepository.findAll()).thenReturn(List.of(trainee1, trainee2));
        when(traineeMapper.toDto(trainee1)).thenReturn(traineeDto1);
        when(traineeMapper.toDto(trainee2)).thenReturn(traineeDto2);

        List<TraineeDto> result = traineeService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getUser().getFistName());
        assertEquals("John", result.get(1).getUser().getFistName());
        assertEquals("New York", result.get(0).getAddress());
        assertEquals("Los Angeles", result.get(1).getAddress());
    }

    @Test
    public void test_shouldGetById() {
        Trainee trainee = new Trainee(1L, 1L, LocalDate.of(2000, 1, 15), "New York");
        UserDto userDto = new UserDto("John", "Smith", true);
        TraineeDto traineeDto = new TraineeDto(userDto, LocalDate.of(2000, 1, 15), "New York");

        when(traineeRepository.findById(1L)).thenReturn(Optional.of(trainee));
        when(traineeMapper.toDto(trainee)).thenReturn(traineeDto);

        Optional<TraineeDto> result = traineeService.getById(1L);

        assertTrue(result.isPresent());
        assertEquals("New York", result.get().getAddress());
        assertEquals(LocalDate.of(2000, 1, 15), result.get().getDateOfBirth());
    }

    @Test
    public void test_shouldNotGetById_whenNotFound() {
        when(traineeRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<TraineeDto> result = traineeService.getById(1L);

        assertTrue(result.isEmpty());
    }

    @Test
    public void test_shouldDeleteById() {
        Long traineeId = 1L;
        traineeService.deleteById(traineeId);

        verify(traineeRepository, times(1)).deleteById(traineeId);
    }
}
