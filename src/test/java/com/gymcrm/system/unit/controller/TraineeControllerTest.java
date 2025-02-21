package com.gymcrm.system.unit.controller;

import com.gymcrm.system.controller.TraineeController;
import com.gymcrm.system.service.TraineeService;
import com.gymcrm.system.service.dto.TraineeDto;
import com.gymcrm.system.service.validate.TraineeRequestValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TraineeControllerTest {

    private TraineeController traineeController;
    private TraineeService traineeService;
    private TraineeRequestValidator traineeRequestValidator;

    @BeforeEach
    void setUp() {
        traineeService = mock(TraineeService.class);
        traineeRequestValidator = mock(TraineeRequestValidator.class);
        traineeController = new TraineeController(traineeService, traineeRequestValidator);
    }

    @Test
    void create_ShouldValidateAndSaveTrainee() {
        TraineeDto traineeDto = new TraineeDto();

        when(traineeService.create(traineeDto)).thenReturn(traineeDto);

        TraineeDto result = traineeController.create(traineeDto);

        verify(traineeRequestValidator).validateCreateRequest(traineeDto);
        verify(traineeService).create(traineeDto);
        assertEquals(traineeDto, result);
    }

    @Test
    void update_ShouldUpdateTrainee() {
        Long traineeId = 1L;
        TraineeDto traineeDto = new TraineeDto();

        when(traineeService.update(traineeId, traineeDto)).thenReturn(traineeDto);

        TraineeDto result = traineeController.update(traineeId, traineeDto);

        verify(traineeService).update(traineeId, traineeDto);
        assertEquals(traineeDto, result);
    }

    @Test
    void getById_ShouldReturnTrainee_WhenExists() {
        Long traineeId = 1L;
        TraineeDto traineeDto = new TraineeDto();
        when(traineeService.getById(traineeId)).thenReturn(Optional.of(traineeDto));

        Optional<TraineeDto> result = traineeController.getById(traineeId);

        verify(traineeService).getById(traineeId);
        assertTrue(result.isPresent());
        assertEquals(traineeDto, result.get());
    }

    @Test
    void getById_ShouldReturnEmpty_WhenNotExists() {
        Long traineeId = 1L;
        when(traineeService.getById(traineeId)).thenReturn(Optional.empty());

        Optional<TraineeDto> result = traineeController.getById(traineeId);

        verify(traineeService).getById(traineeId);
        assertFalse(result.isPresent());
    }

    @Test
    void getAll_ShouldReturnListOfTrainees() {
        List<TraineeDto> traineeList = List.of(new TraineeDto(), new TraineeDto());
        when(traineeService.getAll()).thenReturn(traineeList);

        List<TraineeDto> result = traineeController.getAll();

        verify(traineeService).getAll();
        assertEquals(2, result.size());
    }

    @Test
    void delete_ShouldDeleteTrainee() {
        Long traineeId = 1L;

        traineeController.delete(traineeId);

        verify(traineeService).deleteById(traineeId);
    }
}
