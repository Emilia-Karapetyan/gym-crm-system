package com.gymcrm.system.service;

import com.gymcrm.system.persistance.entity.Trainer;
import com.gymcrm.system.persistance.repository.TrainerRepository;
import com.gymcrm.system.service.dto.TrainerDto;
import com.gymcrm.system.service.dto.UserDto;
import com.gymcrm.system.service.exception.EntityNotFoundException;
import com.gymcrm.system.service.mapper.TrainerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrainerService.class);

    private final TrainerRepository trainerRepository;
    private final UserService userService;
    private final TrainerMapper trainerMapper;

    public TrainerService(TrainerRepository trainerRepository, UserService userService, TrainerMapper trainerMapper) {
        this.trainerRepository = trainerRepository;
        this.userService = userService;
        this.trainerMapper = trainerMapper;
    }

    public TrainerDto create(TrainerDto trainerDto) {
        UserDto userDto = userService.create(trainerDto.getUser());
        Trainer trainer = trainerMapper.toEntity(trainerDto, userDto);
        LOGGER.info("Trainer created successfully: {}", trainer);

        return trainerMapper.toDto(trainerRepository.save(trainer));
    }

    public TrainerDto update(Long id, TrainerDto traineeDto) {
        return trainerRepository.findById(id)
                .map(trainer -> {
                    trainer.setUserId(traineeDto.getUser().getId());
                    trainer.setSpecialization(traineeDto.getSpecialization());
                    LOGGER.info("Trainer with id {} updated successfully", id);

                    return trainerRepository.save(trainer);
                }).map(trainerMapper::toDto)
                .orElseThrow(() -> {
                    LOGGER.error("Trainer with id {} not found", id);
                    return new EntityNotFoundException("Trainer not found");
                });
    }

    public Optional<TrainerDto> getById(Long id) {
        LOGGER.info("Fetching trainer by id: {}", id);
        return trainerRepository.findById(id).map(trainerMapper::toDto);
    }

    public List<TrainerDto> getAll() {
        return trainerRepository.findAll()
                .stream()
                .map(trainerMapper::toDto)
                .toList();
    }
}
