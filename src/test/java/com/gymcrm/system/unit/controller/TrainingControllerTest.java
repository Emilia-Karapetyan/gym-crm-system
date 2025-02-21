package com.gymcrm.system.unit.controller;

import com.gymcrm.system.controller.TrainingController;
import com.gymcrm.system.service.TrainingService;
import com.gymcrm.system.service.dto.TrainingDto;
import com.gymcrm.system.service.validate.TrainingRequestValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrainingControllerTest {

    private TrainingController trainingController;
    private TrainingService trainingService;
    private TrainingRequestValidator trainingRequestValidator;

    @BeforeEach
    void setUp() {
        trainingService = mock(TrainingService.class);
        trainingRequestValidator = mock(TrainingRequestValidator.class);
        trainingController = new TrainingController(trainingService, trainingRequestValidator);
    }

    @Test
    void create_ShouldValidateAndSaveTraining() {
        TrainingDto trainingDto = new TrainingDto();

        when(trainingService.create(trainingDto)).thenReturn(trainingDto);

        TrainingDto result = trainingController.create(trainingDto);

        verify(trainingRequestValidator).validateCreateRequest(trainingDto);
        verify(trainingService).create(trainingDto);
        assertEquals(trainingDto, result);
    }

    @Test
    void getAll_ShouldReturnListOfTrainings() {
        List<TrainingDto> trainingList = List.of(new TrainingDto(), new TrainingDto());
        when(trainingService.getAll()).thenReturn(trainingList);

        List<TrainingDto> result = trainingController.getAll();

        verify(trainingService).getAll();
        assertEquals(2, result.size());
    }

    @Test
    void getById_ShouldReturnTraining_WhenExists() {
        Long trainingId = 1L;
        TrainingDto trainingDto = new TrainingDto();
        when(trainingService.getById(trainingId)).thenReturn(Optional.of(trainingDto));

        Optional<TrainingDto> result = trainingController.getById(trainingId);

        verify(trainingService).getById(trainingId);
        assertTrue(result.isPresent());
        assertEquals(trainingDto, result.get());
    }

    @Test
    void getById_ShouldReturnEmpty_WhenNotExists() {
        Long trainingId = 1L;
        when(trainingService.getById(trainingId)).thenReturn(Optional.empty());

        Optional<TrainingDto> result = trainingController.getById(trainingId);

        verify(trainingService).getById(trainingId);
        assertFalse(result.isPresent());
    }
}
