package com.gymcrm.system.unit.service;

import com.gymcrm.system.persistance.entity.Trainer;
import com.gymcrm.system.persistance.repository.TrainerRepository;
import com.gymcrm.system.service.TrainerService;
import com.gymcrm.system.service.UserService;
import com.gymcrm.system.service.dto.TrainerDto;
import com.gymcrm.system.service.dto.UserDto;
import com.gymcrm.system.service.exception.EntityNotFoundException;
import com.gymcrm.system.service.mapper.TrainerMapper;
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
public class TrainerServiceTest {

    @InjectMocks
    private TrainerService trainerService;
    @Mock
    private TrainerRepository trainerRepository;
    @Mock
    private UserService userService;
    @Mock
    private TrainerMapper trainerMapper;

    @Test
    public void testCreateValidTrainer() {
        UserDto userDto = new UserDto("John", "Smith", true);
        TrainerDto newTrainer = new TrainerDto(1L, userDto, "Cardio expert");

        when(userService.create(any(UserDto.class))).thenReturn(userDto);
        when(trainerMapper.toEntity(newTrainer, userDto)).thenReturn(new Trainer());
        when(trainerRepository.save(any(Trainer.class))).thenReturn(new Trainer());
        when(trainerMapper.toDto(any(Trainer.class))).thenReturn(newTrainer);

        TrainerDto result = trainerService.create(newTrainer);

        assertNotNull(result);
        assertEquals("Cardio expert", result.getSpecialization());
        assertEquals("John", result.getUser().getFistName());
        assertEquals("Smith", result.getUser().getLastName());
    }

    @Test
    public void testUpdateValidTrainer() {
        Trainer trainer = new Trainer(1L, 1L, "Cardio expert");
        UserDto userDto = new UserDto("John", "Smith", true);
        TrainerDto newTrainer = new TrainerDto(1L, userDto, "Strength trainer");
        newTrainer.setId(1L);

        when(trainerRepository.findById(trainer.getId())).thenReturn(Optional.of(trainer));
        when(trainerRepository.save(any(Trainer.class))).thenReturn(trainer);
        when(trainerMapper.toDto(any(Trainer.class))).thenReturn(newTrainer);

        TrainerDto result = trainerService.update(newTrainer.getId(), newTrainer);

        assertNotNull(result);
        assertEquals("Strength trainer", result.getSpecialization());
    }

    @Test
    public void testUpdateTrainerNotFound() {
        UserDto userDto = new UserDto("John", "Smith", true);
        TrainerDto trainerDto = new TrainerDto(1L, userDto, "Strength trainer");

        when(trainerRepository.findById(1L)).thenReturn(Optional.empty());

        EntityNotFoundException result = assertThrows(EntityNotFoundException.class, () -> {
            trainerService.update(1L, trainerDto);
        });

        assertEquals("Trainer not found", result.getMessage());
    }

    @Test
    public void testGetAll() {
        Trainer trainer1 = new Trainer(1L, 1L, "Strength trainer");
        Trainer trainer2 = new Trainer(2L, 1L, "Cardio expert");
        UserDto userDto = new UserDto("John", "Smith", true);
        TrainerDto trainerDto1 = new TrainerDto(1L, userDto, "Strength trainer");
        TrainerDto trainerDto2 = new TrainerDto(2L, userDto, "Cardio expert");

        when(trainerRepository.findAll()).thenReturn(List.of(trainer1, trainer2));
        when(trainerMapper.toDto(trainer1)).thenReturn(trainerDto1);
        when(trainerMapper.toDto(trainer2)).thenReturn(trainerDto2);

        List<TrainerDto> result = trainerService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getUser().getFistName());
        assertEquals("John", result.get(1).getUser().getFistName());
        assertEquals("Strength trainer", result.get(0).getSpecialization());
        assertEquals("Cardio expert", result.get(1).getSpecialization());
    }

    @Test
    public void testGetByIdSuccess() {
        Trainer trainer = new Trainer(1L, 1L, "Strength trainer");
        UserDto userDto = new UserDto("John", "Smith", true);
        TrainerDto trainerDto = new TrainerDto(1L, userDto, "Strength trainer");

        when(trainerRepository.findById(1L)).thenReturn(Optional.of(trainer));
        when(trainerMapper.toDto(trainer)).thenReturn(trainerDto);

        Optional<TrainerDto> result = trainerService.getById(1L);

        assertTrue(result.isPresent());
        assertEquals("Strength trainer", result.get().getSpecialization());
    }

    @Test
    public void testGetByIdNotFound() {
        when(trainerRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<TrainerDto> result = trainerService.getById(1L);

        assertTrue(result.isEmpty());
    }
}
