package com.gymcrm.system.unit.controller;

import com.gymcrm.system.controller.TrainerController;
import com.gymcrm.system.service.TrainerService;
import com.gymcrm.system.service.dto.TrainerDto;
import com.gymcrm.system.service.validate.TrainerRequestValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrainerControllerTest {

    private TrainerController trainerController;
    private TrainerService trainerService;
    private TrainerRequestValidator trainerRequestValidator;

    @BeforeEach
    void setUp() {
        trainerService = mock(TrainerService.class);
        trainerRequestValidator = mock(TrainerRequestValidator.class);
        trainerController = new TrainerController(trainerService, trainerRequestValidator);
    }

    @Test
    void create_ShouldValidateAndSaveTrainer() {
        TrainerDto trainerDto = new TrainerDto();

        when(trainerService.create(trainerDto)).thenReturn(trainerDto);

        TrainerDto result = trainerController.create(trainerDto);

        verify(trainerRequestValidator).validateCreateRequest(trainerDto);
        verify(trainerService).create(trainerDto);
        assertEquals(trainerDto, result);
    }

    @Test
    void update_ShouldUpdateTrainer() {
        Long trainerId = 1L;
        TrainerDto trainerDto = new TrainerDto();

        when(trainerService.update(trainerId, trainerDto)).thenReturn(trainerDto);

        TrainerDto result = trainerController.update(trainerId, trainerDto);

        verify(trainerService).update(trainerId, trainerDto);
        assertEquals(trainerDto, result);
    }

    @Test
    void get_ShouldReturnTrainer_WhenExists() {
        Long trainerId = 1L;
        TrainerDto trainerDto = new TrainerDto();
        when(trainerService.getById(trainerId)).thenReturn(Optional.of(trainerDto));

        Optional<TrainerDto> result = trainerController.get(trainerId);

        verify(trainerService).getById(trainerId);
        assertTrue(result.isPresent());
        assertEquals(trainerDto, result.get());
    }

    @Test
    void get_ShouldReturnEmpty_WhenNotExists() {
        Long trainerId = 1L;
        when(trainerService.getById(trainerId)).thenReturn(Optional.empty());

        Optional<TrainerDto> result = trainerController.get(trainerId);

        verify(trainerService).getById(trainerId);
        assertFalse(result.isPresent());
    }

    @Test
    void getAll_ShouldReturnListOfTrainers() {
        List<TrainerDto> trainerList = List.of(new TrainerDto(), new TrainerDto());
        when(trainerService.getAll()).thenReturn(trainerList);

        List<TrainerDto> result = trainerController.getAll();

        verify(trainerService).getAll();
        assertEquals(2, result.size());
    }
}
