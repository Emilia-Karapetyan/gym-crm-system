package com.gymcrm.system.unit.service;

import com.gymcrm.system.persistance.entity.Training;
import com.gymcrm.system.persistance.repository.TrainingRepository;
import com.gymcrm.system.service.TrainingService;
import com.gymcrm.system.service.dto.*;
import com.gymcrm.system.service.mapper.TrainingMapper;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TrainingServiceTest {

    @InjectMocks
    private TrainingService trainingService;
    @Mock
    private TrainingRepository trainingRepository;
    @Mock
    private TrainingMapper trainingMapper;

    @Test
    public void testCreateValidTraining() {
        UserDto userDto1 = new UserDto("John", "Smith", true);
        UserDto userDto2 = new UserDto("Sam", "Smith", true);
        TraineeDto traineeDto = new TraineeDto(userDto1, LocalDate.of(2000, 1, 15), "New York");
        TrainerDto trainerDto = new TrainerDto(1L, userDto2, "Cardio expert");
        TrainingTypeDto trainingTypeDto = new TrainingTypeDto(1L, "full");
        TrainingDto trainingDto = new TrainingDto(traineeDto, trainerDto, "Cardio", trainingTypeDto, LocalDate.of(2025, 3, 15), "2 hour");

        when(trainingMapper.toEntity(trainingDto)).thenReturn(new Training());
        when(trainingRepository.save(any(Training.class))).thenReturn(new Training());
        when(trainingMapper.toDto(any(Training.class))).thenReturn(trainingDto);

        TrainingDto result = trainingService.create(trainingDto);

        assertNotNull(result);
        assertEquals(result.getTrainingName(), "Cardio");
        assertEquals(result.getTrainingType(), trainingTypeDto);
        assertEquals(result.getTrainingDuration(), "2 hour");
        assertEquals(result.getTrainingDate(), LocalDate.of(2025, 3, 15));
    }


    @Test
    public void testGetAll() {
        UserDto userDto1 = new UserDto("John", "Smith", true);
        UserDto userDto2 = new UserDto("Sam", "Smith", true);
        TrainerDto trainerDto1 = new TrainerDto(1L, userDto1, "Strength trainer");
        TrainerDto trainerDto2 = new TrainerDto(2L, userDto2, "Cardio expert");

        TraineeDto traineeDto1 = new TraineeDto(userDto1, LocalDate.of(2000, 1, 15), "New York");
        TraineeDto traineeDto2 = new TraineeDto(userDto2, LocalDate.of(2000, 1, 15), "New York");

        TrainingTypeDto trainingTypeDto1 = new TrainingTypeDto(1L, "full");
        TrainingTypeDto trainingTypeDto2 = new TrainingTypeDto(2L, "part");

        Training training1 = new Training(1L, 1L, 1L, "Cardio", 1L, LocalDate.of(2000, 1, 15), "2 hour");
        Training training2 = new Training(2L, 2L, 2L, "Cardio", 2L, LocalDate.of(2000, 3, 15), "1 hour");

        TrainingDto trainingDto1 = new TrainingDto(traineeDto1, trainerDto1, "Cardio", trainingTypeDto1, LocalDate.of(2025, 3, 15), "2 hour");
        TrainingDto trainingDto2 = new TrainingDto(traineeDto2, trainerDto2, "Pilates", trainingTypeDto2, LocalDate.of(2025, 3, 15), "1 hour");

        when(trainingRepository.findAll()).thenReturn(List.of(training1, training2));
        when(trainingMapper.toDto(training1)).thenReturn(trainingDto1);
        when(trainingMapper.toDto(training2)).thenReturn(trainingDto2);

        List<TrainingDto> result = trainingService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Cardio", result.get(0).getTrainingName());
        assertEquals("Pilates", result.get(1).getTrainingName());
        assertEquals("full", result.get(0).getTrainingType().getName());
        assertEquals("part", result.get(1).getTrainingType().getName());
        assertEquals("John", result.get(0).getTrainee().getUser().getFistName());
        assertEquals("Sam", result.get(1).getTrainer().getUser().getFistName());
    }

    @Test
    public void testGetByIdSuccess() {
        UserDto userDto1 = new UserDto("John", "Smith", true);
        UserDto userDto2 = new UserDto("Sam", "Smith", true);
        Training training = new Training(1L, 1L, 1L, "Cardio", 1L, LocalDate.of(2000, 1, 15), "2 hour");
        TraineeDto traineeDto = new TraineeDto(userDto1, LocalDate.of(2000, 1, 15), "New York");
        TrainerDto trainerDto = new TrainerDto(1L, userDto2, "Strength trainer");
        TrainingTypeDto trainingTypeDto = new TrainingTypeDto(1L, "full");

        TrainingDto trainingDto = new TrainingDto(traineeDto, trainerDto, "Cardio", trainingTypeDto, LocalDate.of(2025, 3, 15), "2 hour");

        when(trainingRepository.findById(1L)).thenReturn(Optional.of(training));
        when(trainingMapper.toDto(training)).thenReturn(trainingDto);

        Optional<TrainingDto> result = trainingService.getById(1L);

        assertTrue(result.isPresent());
        assertEquals("Cardio", result.get().getTrainingName());
        assertEquals("full", result.get().getTrainingType().getName());
        assertEquals("John", result.get().getTrainee().getUser().getFistName());
        assertEquals("Strength trainer", result.get().getTrainer().getSpecialization());
    }

    @Test
    public void testGetByIdNotFound() {
        when(trainingRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<TrainingDto> result = trainingService.getById(1L);

        assertTrue(result.isEmpty());
    }
}
