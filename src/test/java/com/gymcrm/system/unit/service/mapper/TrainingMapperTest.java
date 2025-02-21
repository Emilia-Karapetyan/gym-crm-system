package com.gymcrm.system.unit.service.mapper;

import com.gymcrm.system.persistance.entity.Trainee;
import com.gymcrm.system.persistance.entity.Trainer;
import com.gymcrm.system.persistance.entity.Training;
import com.gymcrm.system.persistance.entity.TrainingType;
import com.gymcrm.system.persistance.repository.TraineeRepository;
import com.gymcrm.system.persistance.repository.TrainerRepository;
import com.gymcrm.system.persistance.repository.TrainingTypeRepository;
import com.gymcrm.system.service.dto.TraineeDto;
import com.gymcrm.system.service.dto.TrainerDto;
import com.gymcrm.system.service.dto.TrainingDto;
import com.gymcrm.system.service.dto.TrainingTypeDto;
import com.gymcrm.system.service.mapper.TraineeMapper;
import com.gymcrm.system.service.mapper.TrainerMapper;
import com.gymcrm.system.service.mapper.TrainingMapper;
import com.gymcrm.system.service.mapper.TrainingTypeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TrainingMapperTest {

    @Mock
    private TrainingTypeRepository trainingTypeRepository;

    @Mock
    private TraineeRepository traineeRepository;

    @Mock
    private TrainerRepository trainerRepository;

    @Mock
    private TrainingTypeMapper trainingTypeMapper;

    @Mock
    private TraineeMapper traineeMapper;

    @Mock
    private TrainerMapper trainerMapper;

    private TrainingMapper trainingMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        trainingMapper = new TrainingMapper(trainingTypeRepository, traineeRepository, trainerRepository,
                trainingTypeMapper, traineeMapper, trainerMapper);
    }

    @Test
    void toDto_ShouldMapTrainingToTrainingDto_WhenAllEntitiesExist() {
        Training training = new Training(1L, 2L, 3L, "Yoga Session", 4L, LocalDate.of(2025, 2, 10), "60 minutes");
        Trainee trainee = new Trainee(2L, 5L, LocalDate.of(2000, 5, 15), "123 Street");
        Trainer trainer = new Trainer(3L, 6L, "Yoga Instructor");
        TrainingType trainingType = new TrainingType(4L, "Yoga");

        TraineeDto traineeDto = new TraineeDto(2L, null, LocalDate.of(2000, 5, 15), "123 Street");
        TrainerDto trainerDto = new TrainerDto(3L, null, "Yoga Instructor");
        TrainingTypeDto trainingTypeDto = new TrainingTypeDto(4L, "Yoga");

        when(traineeRepository.findById(2L)).thenReturn(Optional.of(trainee));
        when(trainerRepository.findById(3L)).thenReturn(Optional.of(trainer));
        when(trainingTypeRepository.findById(4L)).thenReturn(Optional.of(trainingType));

        when(traineeMapper.toDto(trainee)).thenReturn(traineeDto);
        when(trainerMapper.toDto(trainer)).thenReturn(trainerDto);
        when(trainingTypeMapper.toDto(trainingType)).thenReturn(trainingTypeDto);

        TrainingDto result = trainingMapper.toDto(training);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Yoga Session", result.getTrainingName());
        assertEquals("60 minutes", result.getTrainingDuration());
        assertEquals(LocalDate.of(2025, 2, 10), result.getTrainingDate());

        assertNotNull(result.getTrainee());
        assertEquals(2L, result.getTrainee().getId());

        assertNotNull(result.getTrainer());
        assertEquals(3L, result.getTrainer().getId());

        assertNotNull(result.getTrainingType());
        assertEquals(4L, result.getTrainingType().getId());
    }

    @Test
    void toDto_ShouldHandleMissingReferences() {
        Training training = new Training(1L, 2L, 3L, "Pilates Class", 4L, LocalDate.of(2025, 2, 15), "45 minutes");

        when(traineeRepository.findById(2L)).thenReturn(Optional.empty());
        when(trainerRepository.findById(3L)).thenReturn(Optional.empty());
        when(trainingTypeRepository.findById(4L)).thenReturn(Optional.empty());

        TrainingDto result = trainingMapper.toDto(training);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Pilates Class", result.getTrainingName());
        assertEquals("45 minutes", result.getTrainingDuration());
        assertEquals(LocalDate.of(2025, 2, 15), result.getTrainingDate());

        assertNull(result.getTrainee());
        assertNull(result.getTrainer());
        assertNull(result.getTrainingType());
    }

    @Test
    void toEntity_ShouldMapTrainingDtoToTrainingEntity() {
        TraineeDto traineeDto = new TraineeDto(1L, null, LocalDate.of(2005, 3, 5), "1 Street");
        TrainerDto trainerDto = new TrainerDto(2L, null, "Strength Coach");
        TrainingTypeDto trainingTypeDto = new TrainingTypeDto(4L, "Strength Training");

        TrainingDto trainingDto = new TrainingDto();
        trainingDto.setId(1L);
        trainingDto.setTrainee(traineeDto);
        trainingDto.setTrainer(trainerDto);
        trainingDto.setTrainingName("Strength Session");
        trainingDto.setTrainingType(trainingTypeDto);
        trainingDto.setTrainingDate(LocalDate.of(2025, 3, 20));
        trainingDto.setTrainingDuration("75 minutes");

        Training result = trainingMapper.toEntity(trainingDto);

        assertNotNull(result);
        assertEquals(1L, result.getTraineeId());
        assertEquals(2L, result.getTrainerId());
        assertEquals("Strength Session", result.getTrainingName());
        assertEquals(4L, result.getTrainingTypeId());
        assertEquals(LocalDate.of(2025, 3, 20), result.getTrainingDate());
        assertEquals("75 minutes", result.getTrainingDuration());
    }
}
