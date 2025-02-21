package com.gymcrm.system.unit.service;

import com.gymcrm.system.persistance.entity.TrainingType;
import com.gymcrm.system.persistance.repository.TrainingTypeRepository;
import com.gymcrm.system.service.TrainingTypeService;
import com.gymcrm.system.service.dto.TrainingTypeDto;
import com.gymcrm.system.service.mapper.TrainingTypeMapper;
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
public class TrainingTypeServiceTest {

    @InjectMocks
    private TrainingTypeService trainingTypeService;
    @Mock
    private TrainingTypeRepository trainingTypeRepository;
    @Mock
    private TrainingTypeMapper trainingTypeMapper;

    @Test
    public void test_shouldCreateTrainingType() {
        TrainingTypeDto trainingType = new TrainingTypeDto(1L, "Yoga");

        when(trainingTypeMapper.toEntity(trainingType)).thenReturn(new TrainingType());
        when(trainingTypeRepository.save(any(TrainingType.class))).thenReturn(new TrainingType());
        when(trainingTypeMapper.toDto(any(TrainingType.class))).thenReturn(trainingType);

        TrainingTypeDto result = trainingTypeService.create(trainingType);

        assertNotNull(result);
        assertEquals("Yoga", result.getName());
    }

    @Test
    public void test_shouldGetAllTrainingTypes() {
        TrainingType trainingType1 = new TrainingType(1L, "Yoga");
        TrainingType trainingType2 = new TrainingType(2L, "Pilates");
        TrainingTypeDto trainingTypeDto1 = new TrainingTypeDto(1L, "Yoga");
        TrainingTypeDto trainingTypeDto2 = new TrainingTypeDto(2L, "Pilates");

        when(trainingTypeRepository.findAll()).thenReturn(List.of(trainingType1, trainingType2));
        when(trainingTypeMapper.toDto(trainingType1)).thenReturn(trainingTypeDto1);
        when(trainingTypeMapper.toDto(trainingType2)).thenReturn(trainingTypeDto2);

        List<TrainingTypeDto> result = trainingTypeService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Yoga", result.get(0).getName());
        assertEquals("Pilates", result.get(1).getName());
    }

    @Test
    public void test_shouldGetById() {
        TrainingType trainingType = new TrainingType(1L, "Yoga");
        TrainingTypeDto trainingTypeDto = new TrainingTypeDto(1L, "Yoga");

        when(trainingTypeRepository.findById(1L)).thenReturn(Optional.of(trainingType));
        when(trainingTypeMapper.toDto(trainingType)).thenReturn(trainingTypeDto);

        Optional<TrainingTypeDto> result = trainingTypeService.getById(1L);

        assertTrue(result.isPresent());
        assertEquals("Yoga", result.get().getName());
    }

    @Test
    public void test_shouldNotGetById_whenNotFound() {
        when(trainingTypeRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<TrainingTypeDto> result = trainingTypeService.getById(1L);

        assertTrue(result.isEmpty());
    }
}
