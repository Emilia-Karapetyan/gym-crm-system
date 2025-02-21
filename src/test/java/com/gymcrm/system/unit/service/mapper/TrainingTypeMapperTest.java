package com.gymcrm.system.unit.service.mapper;

import com.gymcrm.system.persistance.entity.TrainingType;
import com.gymcrm.system.service.dto.TrainingTypeDto;
import com.gymcrm.system.service.mapper.TrainingTypeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TrainingTypeMapperTest {

    private TrainingTypeMapper trainingTypeMapper;

    @BeforeEach
    void setUp() {
        trainingTypeMapper = new TrainingTypeMapper();
    }

    @Test
    void toDto_ShouldMapTrainingTypeToTrainingTypeDto() {
        TrainingType trainingType = new TrainingType();
        trainingType.setId(1L);
        trainingType.setName("Yoga");

        TrainingTypeDto result = trainingTypeMapper.toDto(trainingType);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Yoga", result.getName());
    }

    @Test
    void toEntity_ShouldMapTrainingTypeDtoToTrainingType() {
        TrainingTypeDto trainingTypeDto = new TrainingTypeDto();
        trainingTypeDto.setId(1L);
        trainingTypeDto.setName("Pilates");

        TrainingType result = trainingTypeMapper.toEntity(trainingTypeDto);

        assertNotNull(result);
        assertEquals("Pilates", result.getName());
    }
}
